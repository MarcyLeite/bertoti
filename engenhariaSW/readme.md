# Engenharia de Software
Essa pasta é referente as aulas de Engenharia de Software do segundo semestre na FATEC, pelo professor Giuliano ARAUJO BERTOTI

## Texto 1
We see three critical differences between programming and software engineering: time, scale, and the trade-offs at play. On a software engineering project, engineers need to be more concerned with the passage of time and the eventual need for change. In a software engineering organization, we need to be more concerned about scale and efficiency, both for the software we produce as well as for the organization that is producing it. Finally, as software engineers, we are asked to make more complex decisions with higher-stakes outcomes, often based on imprecise estimates of time and growth.

### Comentario
Basicamente, o engenheiro de software prepara o projeto para que a manutenção do código dele seja melhor de lidar, tornando o desenvolvimento dele mais escalavel e efiente, tanto para os desenvolvedores e para a organização. Também, o engenheiro de software precisa deve tomar desições mais precisas e arriscadas, levando em consideração o tempo de desenvolvimento e a expansão do projeto.

## Texto 2
Within Google, we sometimes say, “Software engineering is programming integrated over time.” Programming is certainly a significant part of software engineering: after all, programming is how you generate new software in the first place. If you accept this distinction, it also becomes clear that we might need to delineate between programming tasks (development) and software engineering tasks (development, modification, maintenance). The addition of time adds an important new dimension to programming. Cubes aren’t squares, distance isn’t velocity. Software engineering isn’t programming.

### Comentario
O texto 2 diferencia engenharia de software de programação. Mesmo programação sendo uma das bases que fazem parte da engenharia de software, somente ela não leva em consideração o escopo do projeto. A engenharia de software leva em consideração o tamanho do projeto, sua capacidade de crescer e o tempo de desenvolvimento (como dito no texto 1), em um escopo maior, bem diferente do programador apenas preocupado com o desenvolvimento em si.

### Exemplos
1. R é uma linguagem de programação feita para trabalhos academicos, sendo mais acessivel com menos usos;
2. Godot é uma engine de desenvolvimento de jogo open source, sendo mais acessivel sendo menos completo;
3. VSCode é um editor de texto, que troca a acessibilidade de IDEs por escabilidade, com sua funcionalidade e biblioteca de plugins.

## 10 Heurísticas

### 1. Visibilidade do status do Sistema
Em um sistema digital, é importante existir maneiras visuais de situar o usuario a posição que ele está dentre um sistema. Por exemplo, em um site de exibição de video como o youtube, ao estar vendo uma playlist, é possivel saber qual video da playlist está vendo, quais deles passou antes, e quais estão para passar.

### 2. Compatilibidade entre o sistema e o mundo real
O usuario de um sistema deve reconhecer o que o sistema faz e seus elementos, de forma compativel com sua vida real. Além de compreender o idioma do texto, é importante a utilização de simbolos globais para facilitar o reconhecimento do usuario ao simbolo e sua usabilidade.

### 3. Controle e liberdade ao usuario
O sistema deve dar ao usuario o liberdade da utilização do sistema, com a segurança de reverter as ações errada. Por exemplo, em um email. o usuario deve poder apagar emails indesejados, mas também a liberdade de ir para lixeira e regatar um email deletado.

### 4. Consistencia e Padronização
Uma interface de um sistema deve parecer e se comportar de maneira parecida em relação a outra. Isso torna a experiencia do usuario mais interessante e simples, impedindo ele que se perca  no sistema.

### 5. Prevenção de erros
É comum que o usuario cometa erros, seja por falta de atenção ou por uma mal entendimento do sistema. Por isso, ações de grande impacto do sistema deve conter uma maneira de previnir que usuario tenha problemas por executar essa ação sem querer.

### 6. Reconhecimento no lugar de Memorização
Um sistema deve diminuir a quantidade de informações que um usuario deve memorizar. Por exemplo, sites de e-commerce costumam ter layout bem similares, com o nome do site, barra de navegação, login e carrinho de usuario no canto superior da tela, exibindo a imagem do pruduto na esquerda, com a descrição e opção de compra na direita, etc.

### 7. Eficiencia e flexibidade de uso
O ideal é que a interface seja útil tanto para usuario leigos quanto para os mais experientes no uso do sistema. Um usuario experiente, consegue usar atalhos do sistema de uma forma mais efetiva, quanto o leigo não sabe que isso existe ainda, mas não deve impedir de utilizar o sistema.

### 8. Estética e design minimalista
Menos informação na tela e destaque para as mais importantes auxiliam o usuario a não se perder dentro do sistema. Por exemplo, um blog mostra as informações do autor de um post no canto esquerdo de forma discreta, mas o conteudo do post (a parte importante), é centralizado e com mais destaque.

### 9. Ajude os usuários a reconhecer diagnosticar e recuperar de erros
Um feedback visual explicando ou auxiliando o usuario que ele fez algo errado, um grande exemplo disso é a mensagem de "usuario e senha não encontrados" ao inserir a senha errada no login de um sistema.

### 10. Ajuda e documentação
Importante caso o usuario tenha mais dificuldade de utilizar o sistema, a documentação de utilização pode resolver algumas duvidas.