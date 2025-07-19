package com.tp1.dotsandboxes;

import com.badlogic.gdx.graphics.Texture;

public enum TipoAtaque {
    TIRO_NORMAL(1, 5, "imagens/MegaMan/armas/busterNormal.png", true, 4, 16, 0, 0, 16, 15, true),
    TIRO_AZUL(2, 5, "imagens/MegaMan/armas/busterAzul.png", true, 10, 16, 0, 0, 16, 15, true),
    TIRO_VERDE(3, 5, "imagens/MegaMan/armas/busterVerde.png", false, 4, 16, 0, 0, 16, 15, true),
    TIRO_ROSA(4, 5, "imagens/MegaMan/armas/busterRosa.png", false, 4, 16, 0, 0, 16, 15, true);

    private final int dano;
    private final float velocidade;
    private final String caminhoTextura;
    private final boolean disponivel;
    private final int qtdFrames;
    private final int incrementa;
    private final int cordX;
    private final int cordY;
    private final int largura;
    private final int altura;
    private final boolean isMegaMan;
    private Texture textura;

    private TipoAtaque(int dano, float velocidade, String caminhoTextura, boolean disponivel, int qtdFrames,
            int incrementa, int cordX, int cordY, int largura, int altura, boolean isMegaMan) {
        this.dano = dano;
        this.velocidade = velocidade;
        this.caminhoTextura = caminhoTextura;
        this.disponivel = disponivel;
        this.qtdFrames = qtdFrames;
        this.incrementa = incrementa;
        this.cordX = cordX;
        this.cordY = cordY;
        this.largura = largura;
        this.altura = altura;
        this.isMegaMan = isMegaMan;
    }

    public int getDano() {
        return dano;
    }

    public float getVelocidade() {
        return velocidade;
    }

    public Texture getTexture() {
        return textura;
    }

    public boolean getDisponivel(){
        return disponivel;
    }

    public int getQtdFrames() {
        return qtdFrames;
    }

    public int getIncrementa() {
        return incrementa;
    }

    public int getCordX() {
        return cordX;
    }

    public int getCordY() {
        return cordY;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public boolean isMegaMan() {
        return isMegaMan;
    }

    public void carregarTextura() {
        if (textura == null) {
            this.textura = new Texture(caminhoTextura);
        }
    }

    public static void carregarTodas() {
        for (TipoAtaque ataque : values()) {
            ataque.carregarTextura();
        }
    }

}
