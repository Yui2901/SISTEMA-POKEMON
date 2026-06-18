
import java.util.*;

class SistemaBatalha {
    private List<Pokemon> pokemonsDisponiveis;
    private Scanner scanner;

    public SistemaBatalha() {
        this.pokemonsDisponiveis = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        inicializarPokemons();
    }

    private void inicializarPokemons() {
        // Pikachu
        Pokemon pikachu = new Pokemon("Pikachu", 100);
        pikachu.adicionarAtaque(new Ataque("Thunderbolt", 40, 90));
        pikachu.adicionarAtaque(new Ataque("Quick Attack", 25, 100));
        pikachu.adicionarAtaque(new Ataque("Thunder", 55, 70));
        pikachu.adicionarAtaque(new Ataque("Tackle", 20, 95));
        pokemonsDisponiveis.add(pikachu);

        // Charizard
        Pokemon charizard = new Pokemon("Charizard", 120);
        charizard.adicionarAtaque(new Ataque("Flamethrower", 45, 85));
        charizard.adicionarAtaque(new Ataque("Dragon Claw", 35, 90));
        charizard.adicionarAtaque(new Ataque("Fire Blast", 60, 65));
        charizard.adicionarAtaque(new Ataque("Wing Attack", 30, 95));
        pokemonsDisponiveis.add(charizard);

        // Blastoise
        Pokemon blastoise = new Pokemon("Blastoise", 115);
        blastoise.adicionarAtaque(new Ataque("Hydro Pump", 50, 75));
        blastoise.adicionarAtaque(new Ataque("Water Gun", 30, 95));
        blastoise.adicionarAtaque(new Ataque("Surf", 40, 90));
        blastoise.adicionarAtaque(new Ataque("Bite", 25, 90));
        pokemonsDisponiveis.add(blastoise);

        // Venusaur
        Pokemon venusaur = new Pokemon("Venusaur", 110);
        venusaur.adicionarAtaque(new Ataque("Solar Beam", 55, 80));
        venusaur.adicionarAtaque(new Ataque("Vine Whip", 35, 90));
        venusaur.adicionarAtaque(new Ataque("Petal Dance", 45, 85));
        venusaur.adicionarAtaque(new Ataque("Tackle", 20, 95));
        pokemonsDisponiveis.add(venusaur);

        // Gengar
        Pokemon gengar = new Pokemon("Gengar", 95);
        gengar.adicionarAtaque(new Ataque("Shadow Ball", 40, 85));
        gengar.adicionarAtaque(new Ataque("Lick", 25, 90));
        gengar.adicionarAtaque(new Ataque("Night Shade", 35, 95));
        gengar.adicionarAtaque(new Ataque("Hypnosis", 0, 60)); // Ataque de status
        pokemonsDisponiveis.add(gengar);

        // Garchomp
        Pokemon garchomp = new Pokemon("garchomp", 110);
        garchomp.adicionarAtaque(new Ataque("crunch", 40, 85));
        garchomp.adicionarAtaque(new Ataque("dig", 20, 90));
        garchomp.adicionarAtaque(new Ataque("Dragon pulse", 40, 95));
        garchomp.adicionarAtaque(new Ataque("draco meteor", 60, 70));
        pokemonsDisponiveis.add(garchomp);

        // ARTICUNO
        Pokemon articuno = new Pokemon("articuno", 120);
        articuno.adicionarAtaque(new Ataque("ice beam", 60, 85));
        articuno.adicionarAtaque(new Ataque("air cutter", 30, 90));
        articuno.adicionarAtaque(new Ataque("blizard", 60, 90));
        articuno.adicionarAtaque(new Ataque("hurricane", 45, 80));
        pokemonsDisponiveis.add(articuno);
    }

    private void mostrarListaPokemons() {
        System.out.println("\n=== POKÉMONS DISPONÍVEIS ===");
        for (int i = 0; i < pokemonsDisponiveis.size(); i++) {
            System.out.println((i + 1) + ". " + pokemonsDisponiveis.get(i));
        }
    }

    private Pokemon escolherPokemon(String jogador) {
        System.out.println("\n" + jogador + ", escolha seu Pokémon:");
        mostrarListaPokemons();

        int escolha;
        do {
            System.out.print("Digite o número do Pokémon (1-" + pokemonsDisponiveis.size() + "): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, digite um número válido: ");
                scanner.next();
            }
            escolha = scanner.nextInt();
        } while (escolha < 1 || escolha > pokemonsDisponiveis.size());

        // Criar uma cópia do Pokémon escolhido para evitar alterações no original
        Pokemon original = pokemonsDisponiveis.get(escolha - 1);
        Pokemon copia = new Pokemon(original.getNome(), original.getHpMaximo());
        for (Ataque ataque : original.getAtaques()) {
            copia.adicionarAtaque(new Ataque(ataque.getNome(), ataque.getDano(), ataque.getPrecisao()));
        }

        System.out.println(jogador + " escolheu " + copia.getNome() + "!");
        return copia;
    }

    private Ataque escolherAtaque(Pokemon pokemon, String jogador) {
        System.out.println("\n" + jogador + ", escolha um ataque:");
        pokemon.mostrarStatus();

        int escolha;
        do {
            System.out.print("Digite o número do ataque (1-" + pokemon.getAtaques().size() + "): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, digite um número válido: ");
                scanner.next();
            }
            escolha = scanner.nextInt();
        } while (escolha < 1 || escolha > pokemon.getAtaques().size());

        return pokemon.getAtaques().get(escolha - 1);
    }

    private void executarTurno(Pokemon atacante, Pokemon defensor, Ataque ataque, String nomeAtacante) {
        System.out.println("\n" + atacante.getNome() + " usa " + ataque.getNome() + "!");

        if (ataque.acertou()) {
            defensor.receberDano(ataque.getDano());
            System.out.println("O ataque acertou! " + defensor.getNome() + " perdeu " + ataque.getDano() + " HP!");
        } else {
            System.out.println("O ataque errou!");
        }

        System.out.println(defensor.getNome() + " - HP restante: " + defensor.getHp() + "/" + defensor.getHpMaximo());
    }

    private String executarTurnoComResumo(Pokemon atacante, Pokemon defensor, Ataque ataque, String nomeAtacante) {
        System.out.println("\n" + atacante.getNome() + " usa " + ataque.getNome() + "!");

        String resultado;
        if (ataque.acertou()) {
            defensor.receberDano(ataque.getDano());
            System.out.println("O ataque acertou! " + defensor.getNome() + " perdeu " + ataque.getDano() + " HP!");
            resultado = atacante.getNome() + " usou " + ataque.getNome() + " e causou " + ataque.getDano()
                    + " de dano em " + defensor.getNome();
        } else {
            System.out.println("O ataque errou!");
            resultado = atacante.getNome() + " usou " + ataque.getNome() + " mas errou o ataque";
        }

        System.out.println(defensor.getNome() + " - HP restante: " + defensor.getHp() + "/" + defensor.getHpMaximo());
        return resultado;
    }

    public void iniciarBatalha() {
        System.out.println("=== SISTEMA DE BATALHAS POKÉMON ===");
        System.out.println("Bem-vindos, treinadores!");

        // Escolha dos Pokémons
        Pokemon pokemon1 = escolherPokemon("Jogador 1");
        Pokemon pokemon2 = escolherPokemon("Jogador 2");

        System.out.println("\n=== INÍCIO DA BATALHA ===");
        System.out.println(pokemon1.getNome() + " VS " + pokemon2.getNome());

        // Mostrar informações dos Pokémons
        System.out.println("\n=== INFORMAÇÕES DOS POKÉMONS ===");
        System.out.println("\nJogador 1:");
        pokemon1.mostrarStatus();
        System.out.println("\nJogador 2:");
        pokemon2.mostrarStatus();

        // Loop da batalha
        int turno = 1;
        String resumoTurnoAnterior = "";

        while (pokemon1.estaVivo() && pokemon2.estaVivo()) {
            System.out.println("\n" + "=".repeat(30));
            System.out.println("TURNO " + turno);
            System.out.println("=".repeat(30));

            // Mostrar resumo do turno anterior (a partir do turno 2)
            if (turno > 1 && !resumoTurnoAnterior.isEmpty()) {
                System.out.println("\n=== RESUMO DO TURNO ANTERIOR ===");
                System.out.println(resumoTurnoAnterior);
            }

            // Preparar resumo do turno atual
            StringBuilder resumoTurnoAtual = new StringBuilder();

            // Turno do Jogador 1
            if (pokemon1.estaVivo()) {
                Ataque ataque1 = escolherAtaque(pokemon1, "Jogador 1");
                String resultadoAtaque1 = executarTurnoComResumo(pokemon1, pokemon2, ataque1, "Jogador 1");
                resumoTurnoAtual.append("• ").append(resultadoAtaque1).append("\n");

                if (!pokemon2.estaVivo()) {
                    break;
                }
            }

            // Turno do Jogador 2
            if (pokemon2.estaVivo()) {
                Ataque ataque2 = escolherAtaque(pokemon2, "Jogador 2");
                String resultadoAtaque2 = executarTurnoComResumo(pokemon2, pokemon1, ataque2, "Jogador 2");
                resumoTurnoAtual.append("• ").append(resultadoAtaque2);
            }

            // Salvar resumo para o próximo turno
            resumoTurnoAnterior = resumoTurnoAtual.toString();

            turno++;

            // Pausa entre turnos
            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine();
            scanner.nextLine();
        }

        // Resultado da batalha
        System.out.println("\n" + "=".repeat(30));
        System.out.println("FIM DA BATALHA!");
        System.out.println("=".repeat(30));

        if (pokemon1.estaVivo()) {
            System.out.println("🎉 JOGADOR 1 VENCEU com " + pokemon1.getNome() + "!");
        } else {
            System.out.println("🎉 JOGADOR 2 VENCEU com " + pokemon2.getNome() + "!");
        }

        System.out.println("\nObrigado por jogar!");
    }
}
