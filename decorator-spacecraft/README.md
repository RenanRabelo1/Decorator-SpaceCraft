# Decorator SpaceCraft

Jogo educativo de console em Java criado para demonstrar o padrão de projeto GoF **Decorator**. Antes da batalha, o jogador começa com uma nave básica e instala melhorias que envolvem a nave sem alterar sua classe original.

## Objetivo

O projeto transforma uma explicação abstrata em algo visível: cada melhoria é um decorator. Assim, Turbo, Laser, Escudo, Míssil e Reparo podem ser combinados livremente em uma única nave.

```text
Explorer
  + Turbo
  + Laser
  + Míssil
  + Escudo
  + Reparo
```

## O padrão Decorator no jogo

`Spacecraft` é o componente comum. `BasicSpacecraft` é a nave inicial e `SpacecraftDecorator` é a base para as melhorias. Cada decorator recebe outra `Spacecraft`, delega o que ela já faz e adiciona somente sua responsabilidade.

| Classe | Papel no padrão | Efeito |
| --- | --- | --- |
| `Spacecraft` | Component | Contrato da nave: descrição, atributos e habilidades. |
| `BasicSpacecraft` | Concrete Component | Nave Explorer com atributos iniciais. |
| `SpacecraftDecorator` | Decorator | Mantém e delega para a nave decorada. |
| `TurboDecorator` | Concrete Decorator | Adiciona 10 de velocidade. |
| `LaserDecorator` | Concrete Decorator | Adiciona 15 de ataque. |
| `ShieldDecorator` | Concrete Decorator | Adiciona 15 de defesa. |
| `MissileDecorator` | Concrete Decorator | Habilita um míssil de uso único com 40 de dano. |
| `RepairDecorator` | Concrete Decorator | Recupera 5 de vida ao fim da rodada. |

Exemplo de composição:

```java
Spacecraft spacecraft = new RepairDecorator(
        new ShieldDecorator(
                new MissileDecorator(
                        new LaserDecorator(
                                new TurboDecorator(new BasicSpacecraft())
                        )
                )
        )
);
```

Essa composição produz a descrição `Explorer + Turbo + Laser + Míssil + Escudo + Reparo`, com ataque 25, defesa 20, velocidade 20 e míssil disponível.

Em vez de criar subclasses como `ExplorerComTurboELaser`, `ExplorerComEscudo` e todas as combinações possíveis, o Decorator permite acrescentar comportamentos em tempo de execução. A repetição de uma melhoria é permitida intencionalmente: ela torna evidente que cada camada é independente.

## Regras da batalha

- O dano normal é `ataque - defesa`, com mínimo de 1.
- O míssil pode ser disparado uma vez por batalha.
- O reparo acontece depois do ataque inimigo e não ultrapassa a vida máxima da nave.
- A batalha termina quando a vida do jogador ou do inimigo chega a zero.

## Como executar

Pré-requisitos: Java 17 ou superior e Maven.

```bash
mvn test
mvn exec:java -Dexec.mainClass="com.renan.decoratorspacecraft.app.Main"
```

No menu principal, escolha `1` para iniciar a missão. Instale melhorias e escolha `6` para entrar em batalha. O jogo trata letras e opções fora do menu com uma mensagem de erro, sem encerrar a aplicação.

## Estrutura do projeto

```text
src/main/java/com/renan/decoratorspacecraft
├── app/          ponto de entrada
├── controller/   coordena o fluxo do jogo
├── domain/       regras de negócio, batalha, nave e decorators
├── exception/    erros de opção e melhoria inválida
├── factory/      criação da nave básica
├── service/      casos de uso de batalha e melhorias
└── ui/           leitura e apresentação no console
```

## SOLID e GRASP aplicados

- **Responsabilidade única (SRP):** `ConsoleMenu` lê entradas, `ConsoleRenderer` exibe textos, `Battle` guarda o estado da batalha e os serviços executam os casos de uso.
- **Aberto/fechado (OCP):** uma nova melhoria pode ser criada como outro decorator, sem modificar a `BasicSpacecraft`.
- **Inversão de dependência (DIP):** decorators e regras de batalha dependem da abstração `Spacecraft`, não de uma nave concreta.
- **Polimorfismo:** todos os decorators podem ser usados onde uma `Spacecraft` é esperada.
- **Controller (GRASP):** `GameController` recebe as escolhas da interface e coordena os serviços.
- **Creator (GRASP):** `SpacecraftFactory` concentra a criação da nave inicial; `UpgradeService` cria o decorator correspondente à melhoria escolhida.
