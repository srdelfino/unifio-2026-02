FROM ubuntu:24.04

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    mysql-server \
    git \
    curl \
    wget \
    unzip \
    zip \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

CMD ["bash"]