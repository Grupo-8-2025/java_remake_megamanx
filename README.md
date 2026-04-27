# Remake MegaManX

Jogo desenvolvido em Java, inspirado no primeiro título da clássica franquia Mega Man X, com foco na recriação de suas principais mecânicas de jogabilidade, como movimentação e combate.

O projeto foi desenvolvido como trabalho prático da disciplina Linguagem e Técnicas de Programação II, no curso técnico de informática do CEFET-MG, utilizando a biblioteca LibGDX para gerenciamento de recursos gráficos e multimídia.

## Status do Projeto

Este projeto foi desenvolvido para fins acadêmicos e não está mais em manutenção.

Atualmente, podem ocorrer comportamentos inesperados em determinadas situações, como inconsistências no sistema de colisões.

O projeto é mantido aqui como demonstração de conceito e aprendizado.

---

## Objetivos
- Praticar desenvolvimento backend em Java
- Aplicar conceitos de Programação Orientada a Objetos no desenvolvimento de jogos
- Desenvolver um jogo do tipo scrolling (plataforma)
- Recriar as principais mecânicas, regras e elementos visuais do jogo original
- Estudar e implementar o padrão de projeto Iterator

## Funcionalidades
- Movimentação do personagem em plataformas
- Sistema de pulo e dash
- Sistema de tiros e troca de tipos de disparo
- Sistema de controle de vidas
- Presença de inimigos e chefão 
- Sistema de detecção de colisões
- Sistema de condições de vitória e game over

## Tecnologias Usadas
- Java 
- Gradle
- LibGDX
- Padrão de projeto Iterator
- VS Code
- GitHub Copilot
- Live Share

---

## Como Executar

### Requisitos
- JDK 8 ou superior
- Gradle (opcional, caso não utilize o wrapper incluído no projeto)

### Execução Via Terminal
1. Baixe e extraia o arquivo `.zip` do projeto
2. Navegue até o diretório raiz do projeto pelo terminal
3. Execute o comando abaixo:
```
gradlew lwjgl3:run
```

### Execução Via IDE
1. Baixe e extraia o arquivo `.zip` do projeto
2. Abra o projeto em sua IDE de preferência 
3. Aguarde o carregamento e a sincronização das dependências do Gradle
4. Navegue até o seguinte arquivo: `lwjgl3/src/main/java/com/tp2/megamanx/lwjgl3/Lwjgl3Launcher.java`
5. Execute a classe `Lwjgl3Launcher`

---

## Como Jogar

### Objetivo
Derrotar o chefão.

### Controles
- **⬆️ Cima**: Subir plataformas
- **⬅️ Esquerda**: Mover-se para a esquerda
- **➡️ Direita**: Mover-se para a direita
- **⏺ Espaço**: Pular
- **Tecla Shift**: Ativar o dash
- **Tecla X**: Atirar
- **Tecla C**: Trocar de tiro

### Dica
Execute o jogo em modo janela, na resolução inicial, para evitar problemas de escala gráfica.

---

## Observação Importante
Este projeto contou com o apoio de inteligências artificiais generativas durante o desenvolvimento, sendo utilizadas para auxiliar na implementação de código, correção de erros de lógica e aprimoramento da documentação.

---

## Telas do Jogo
Capturas de tela mostrando a interface e a jogabilidade do projeto.

### Tela Inicial
<img width="500" height="331" alt="image" src="https://github.com/user-attachments/assets/af497f8f-bf0d-4e96-8168-7bd73cce434d" />

### Tela do Jogo 1
<img width="500" height="331" alt="image" src="https://github.com/user-attachments/assets/e7204434-38b3-496a-95d9-a760b718a105" />

### Tela do Jogo 2
<img width="500" height="331" alt="image" src="https://github.com/user-attachments/assets/6a77171e-9385-4c6c-9cb8-41e858f94ebb" />

### Telas Finais
<img width="500" height="331" alt="image" src="https://github.com/user-attachments/assets/0ca6f220-2ca9-409b-83f3-2a1f7e949ecc" />
<br>
<img width="500" height="331" alt="Capturar" src="https://github.com/user-attachments/assets/da368392-599e-494d-814d-4c552d6add44" />
