FROM ubuntu:24.04

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    maven \
    mysql-server \
    git \
    git-lfs \
    curl \
    wget \
    unzip \
    zip \
    sudo \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

CMD ["bash"]