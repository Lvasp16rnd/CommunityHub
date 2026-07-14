#!/bin/bash
echo "Inicializando infraestrutura AWS no LocalStack..."

# 1. Criar o Tópico SNS (Alto-falante)
awslocal sns create-topic --name incident-events-topic

# 2. Criar a Fila SQS (Caixa de entrada)
awslocal sqs create-queue --queue-name notification-queue

# 3. Amarrar a Fila no Tópico (Subscribe)
# Isso significa: "Tudo que gritarem no Tópico, jogue dentro desta Fila"
awslocal sns subscribe \
    --topic-arn arn:aws:sns:us-east-1:000000000000:incident-events-topic \
    --protocol sqs \
    --notification-endpoint arn:aws:sqs:us-east-1:000000000000:notification-queue

echo "Infraestrutura inicializada com sucesso!"
