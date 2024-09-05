FROM mcr.microsoft.com/devcontainers/java:1-21-bullseye

# Add a non-root user with sudo access
ARG USERNAME=nickghule
ARG USER_UID=1001
ARG USER_GID=$USER_UID

RUN groupadd --gid $USER_GID $USERNAME \
    && useradd --uid $USER_UID --gid $USER_GID -m $USERNAME \
    && apt-get update \
    && apt-get install -y sudo \
    && echo "$USERNAME ALL=(ALL) NOPASSWD:ALL" > /etc/sudoers.d/$USERNAME \
    && chmod 0440 /etc/sudoers.d/$USERNAME \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* \
    && ln -fs /usr/share/zoneinfo/Asia/Kolkata /etc/localtime 

# Switch to non-root user
USER $USERNAME
SHELL ["/bin/bash", "-c"]


# Optional: Install additional OS packages
# RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
#     && apt-get -y install --no-install-recommends <your-package-list-here> \
#     && apt-get clean && rm -rf /var/lib/apt/lists/*

# Optional: Install global node packages
# RUN su vscode -c "source /usr/local/share/nvm/nvm.sh && npm install -g <your-package-here>" 2>&1


RUN sudo apt-get update && sudo apt-get install -y \
    git \
    curl \
    wget \
    unzip \
    && sudo apt-get clean \
    && sudo rm -rf /var/lib/apt/lists/* \
    && sudo chown -R $USERNAME /usr/local/sdkman \
    && . /usr/local/sdkman/bin/sdkman-init.sh \
    && sdk install maven


COPY ./.bashrc /home/$USERNAME/.bashrc

WORKDIR /workspace

CMD ["/bin/bash"]
