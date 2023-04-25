# Define a imagem base
FROM adoptopenjdk:11-jre-hotspot

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR da aplicação para o diretório de trabalho
COPY target/reccos-admin.jar /app/reccos-admin.jar

# Define o comando a ser executado ao iniciar o container
CMD ["java", "-jar", "reccos-admin.jar"]
