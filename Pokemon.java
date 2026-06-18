
import java.util.*;

class Ataque {
    private String nome;
    private int dano;
    private int precisao; // 0-100 (porcentagem de acerto)
    
    public Ataque(String nome, int dano, int precisao) {
        this.nome = nome;
        this.dano = dano;
        this.precisao = precisao;
    }
    
    public String getNome() { return nome; }
    public int getDano() { return dano; }
    public int getPrecisao() { return precisao; }
    
    public boolean acertou() {
        Random random = new Random();
        return random.nextInt(100) < precisao;
    }
    
    @Override
    public String toString() {
        return String.format("%s (Dano: %d, Precisão: %d%%)", nome, dano, precisao);
    }
}

class Pokemon {
    private String nome;
    private int hp;
    private int hpMaximo;
    private List<Ataque> ataques;
    
    public Pokemon(String nome, int hp) {
        this.nome = nome;
        this.hp = hp;
        this.hpMaximo = hp;
        this.ataques = new ArrayList<>();
    }
    
    public void adicionarAtaque(Ataque ataque) {
        if (ataques.size() < 4) {
            ataques.add(ataque);
        }
    }
    
    public String getNome() { return nome; }
    public int getHp() { return hp; }
    public int getHpMaximo() { return hpMaximo; }
    public List<Ataque> getAtaques() { return ataques; }
    
    public void receberDano(int dano) {
        this.hp = Math.max(0, this.hp - dano);
    }
    
    public boolean estaVivo() {
        return hp > 0;
    }
    
    public void mostrarStatus() {
        System.out.println(nome + " - HP: " + hp + "/" + hpMaximo);
        System.out.println("Ataques disponíveis:");
        for (int i = 0; i < ataques.size(); i++) {
            System.out.println((i + 1) + ". " + ataques.get(i));
        }
    }
    
    @Override
    public String toString() {
        return String.format("%s (HP: %d)", nome, hpMaximo);
    }
}
