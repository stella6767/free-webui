# Free-webUI


https://github.com/user-attachments/assets/42106970-922e-4462-9b90-a9bd3add8af2


## Introduction

Free WebU is a web-based interface that allows you to run and manipulate your local LLM model. 
If you have Ollama installed, and can run a model, you can run this self-hosted app on your local PC and start testing it immediately.

## Tech Stack 🛠️

- `Kotlin with SpringBoot Framework`: for Secure and reliable server
- `spring-data-jpa`: Simplifies the development of creating a JPA-based data access layer
- [`kotlin-jdsl`](https://github.com/line/kotlin-jdsl): Kotlin library that makes it easy to build and execute queries without generated metamodel
- [`Kotlinx html`](https://github.com/Kotlin/kotlinx.html): Kotlin DSL for HTML
- [`HTMX`](https://github.com/bigskysoftware/htmx): No hefty JavaScript frameworks needed—HTMX keeps interactions snappy with simple HTML attributes
- `File-based h2 database`: A lightweight yet powerful database for all your data persistence needs
- [`tailwindcss`](https://github.com/tailwindlabs/tailwindcss): A utility-first CSS framework for rapid UI development.


## Features 🌟

- **Browser-Native AI**: Experience cutting-edge language models running natively within your web browser, eliminating the need for server-side processing or cloud dependencies.
- **Ganranteed Privacy**: With the AI model running locally on your hardware and all data processing happening within your browser, your data and conversations never leave your computer, ensuring your privacy.
- **Offline Accessibility**: Run entirely offline after the initial setup and download, allowing you to engage with AI-powered conversations without an active internet connection.
- **Server Sent Events (SSE)**: Real-time streaming made easy to LLM interactions.
- **Open Source and Customizable**: Build and customize your own AI-powered applications with my project.


## Quickstart 🏁

### via JDK

move to release page -> download jar file -> execute terminal command in your local server 
```angular2html
java -jar ${file path}.jar
```
Check the http://localhost:8080 port in your browser!


### via Docker

If you have Ollama on your computer, use the following commands

```angular2html
 docker pull stella6767/freewebui:0.0.1
 //or using DockerFile

 docker run -d -p 8080:8080 --add-host host.docker.internal:host-gateway ${imageName:tag}
```
It may take some time after the server loads before you can communicate with ollama.

## Contributing 🤝

If you have any suggestions to improve this project, please fork the repo and create a pull request. 
You can also simply open an issue. It's a simple project I made by myself, but I'd love to see it grow into open source. 
Oh, and don't forget to give it a star.
