#!/bin/bash

echo "Definindo permissoes da pasta de código-fonte..."
docker container exec chess-system-java chmod 777 -R /app
sleep 1

echo "Iniciando o app..."
docker container exec -it chess-system-java bash -c "cd /app/bin; java application.Program;"
