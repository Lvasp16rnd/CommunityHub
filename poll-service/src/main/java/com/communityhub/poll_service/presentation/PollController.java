package com.communityhub.poll_service.presentation;

import com.communityhub.poll_service.domain.Poll;
import com.communityhub.poll_service.domain.PollOption;
import com.communityhub.poll_service.domain.Vote;
import com.communityhub.poll_service.infrastructure.PollRepository;
import com.communityhub.poll_service.infrastructure.VoteRepository;
import com.communityhub.poll_service.presentation.dto.PollCreationDTO;
import com.communityhub.poll_service.presentation.dto.VoteDTO;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.dao.DuplicateKeyException;

@RestController
@RequestMapping("/api/polls")
public class PollController {
    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;

    public PollController(PollRepository pollRepository, VoteRepository voteRepository) {
        this.pollRepository = pollRepository;
        this.voteRepository = voteRepository;
    }

    @PostMapping
    public ResponseEntity<?> createPoll(@Valid @RequestBody PollCreationDTO dto) {
        List<PollOption> pollOptions = dto.getOptions().stream()
                .map(text -> new PollOption(text)).toList();

        Poll poll = new Poll(null, dto.getTitle(), dto.getDescription(), pollOptions);

        Poll savedPoll = pollRepository.save(poll);

        return ResponseEntity.ok(savedPoll);
    }

    @PostMapping("/{pollId}/vote")
    public ResponseEntity<String> votePoll(@PathVariable String pollId,
            @RequestBody VoteDTO dto) {

        try {
            // create a vote
            Vote vote = new Vote(null, pollId, dto.getUserId(), dto.getOptionId());
            // save vote
            voteRepository.save(vote);
            // find if poll already exists
            Poll poll = pollRepository.findById(pollId)
                    .orElseThrow(() -> new RuntimeException("Enquete não encontrada"));

            poll.getOptions().stream().filter(opt -> opt.getId().equals(dto.getOptionId()))
                    .findFirst().ifPresent(opt -> opt.setVoteCount(opt.getVoteCount() + 1));

            pollRepository.save(poll);

            return ResponseEntity.ok("Voto computado com sucesso");

        } catch (DuplicateKeyException e) {
            return ResponseEntity.badRequest().body("Erro: Você já votou nesta enquete!");
        }

    }

}
