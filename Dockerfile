
FROM mcr.microsoft.com/devcontainers/base:ubuntu-24.04

# OpenJDK 21
ENV JAVA_VERSION=21
ENV JAVA_HOME=/usr/lib/jvm/java-${JAVA_VERSION}-openjdk-arm64
ENV PATH="${JAVA_HOME}/bin:${PATH}"

RUN apt-get update && apt-get install -y openjdk-${JAVA_VERSION}-jdk  \
 && apt-get clean && rm -rf /var/lib/apt/lists/*

# Maven
ENV MAVEN_VERSION=3.9.11
ENV MAVEN_MAJOR_VERSION=3
ENV MAVEN_HOME=/opt/apache-maven-${MAVEN_VERSION}
ENV PATH="${MAVEN_HOME}/bin:${PATH}"

RUN wget https://dlcdn.apache.org/maven/maven-${MAVEN_MAJOR_VERSION}/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
 && tar xzvf apache-maven-${MAVEN_VERSION}-bin.tar.gz -C /opt/   \
 && rm apache-maven-${MAVEN_VERSION}-bin.tar.gz

# # Storm release
ENV STORM_VERSION=2.8.3
ENV STORM_HOME=/opt/apache-storm-${STORM_VERSION}
ENV PATH="${STORM_HOME}/bin:${PATH}"

RUN wget https://dlcdn.apache.org/storm/apache-storm-${STORM_VERSION}/apache-storm-${STORM_VERSION}.tar.gz  \
 && tar xzvf apache-storm-${STORM_VERSION}.tar.gz -C /opt/  \
 && rm apache-storm-${STORM_VERSION}.tar.gz

# Storm source code
ENV STORM_TAG=v${STORM_VERSION}

RUN git clone --branch "${STORM_TAG}" --depth 1 https://github.com/apache/storm.git  \
 && cd storm \
 && mvn clean install -DskipTests=true 
