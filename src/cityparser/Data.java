package cityparser;

import java.util.HashMap;
import java.util.Vector;

/**
 * Created by Afonso on 26/05/2016.
 */
public class Data implements java.io.Serializable {

    public static HashMap<String, Integer> constructionCosts = new HashMap<>();

    static {
        /**
         * Nota: Os dados seguintes sao baseados na Portaria nº 323/2013 de 4 de dezembro que fixa o custo por metro
         * quadrado de construcao de uma habitacao para cada concelho de Portugal. Para efeitos de simplificacao,
         * supomos que todos os tribunais tem o mesmo tamanho, 90m x 60m (total de 5400m). A portaria especificada pode
         * ser consultada no seguinte link: https://dre.pt/application/dir/pdf1sdip/2013/12/23500/0664406644.pdf
         */
        constructionCosts.put("Outros", 3425814);
        constructionCosts.put("Aveiro", 4325724);
        constructionCosts.put("Beja", 4325724);
        constructionCosts.put("Braga", 4325724);
        constructionCosts.put("Bragança", 4325724);
        constructionCosts.put("Castelo Branco", 4325724);
        constructionCosts.put("Coimbra", 4325724);
        constructionCosts.put("Évora", 4325724);
        constructionCosts.put("Faro", 4325724);
        constructionCosts.put("Guarda", 4325724);
        constructionCosts.put("Leiria", 4325724);
        constructionCosts.put("Lisboa", 4325724);
        constructionCosts.put("Portalegre", 4325724);
        constructionCosts.put("Porto", 4325724);
        constructionCosts.put("Santarém", 4325724);
        constructionCosts.put("Setúbal", 4325724);
        constructionCosts.put("Viana do Castelo", 4325724);
        constructionCosts.put("Vila Real", 4325724);
        constructionCosts.put("Almada", 4325724);
        constructionCosts.put("Amadora", 4325724);
        constructionCosts.put("Barreiro", 4325724);
        constructionCosts.put("Cascais", 4325724);
        constructionCosts.put("Gondomar", 4325724);
        constructionCosts.put("Loures", 4325724);
        constructionCosts.put("Maia", 4325724);
        constructionCosts.put("Matosinhos", 4325724);
        constructionCosts.put("Moita", 4325724);
        constructionCosts.put("Montijo", 4325724);
        constructionCosts.put("Odivelas", 4325724);
        constructionCosts.put("Oeiras", 4325724);
        constructionCosts.put("Póvoa de Varzim", 4325724);
        constructionCosts.put("Seixal", 4325724);
        constructionCosts.put("Sintra", 4325724);
        constructionCosts.put("Valongo", 4325724);
        constructionCosts.put("Vila do Conde", 4325724);
        constructionCosts.put("Vila Franca de Xira", 4325724);
        constructionCosts.put("Vila Nova de Gaia", 4325724);
        constructionCosts.put("Calheta (Madeira)", 4325724);
        constructionCosts.put("Câmara de Lobos", 4325724);
        constructionCosts.put("Funchal", 4325724);
        constructionCosts.put("Machico", 4325724);
        constructionCosts.put("Ponta do Sol", 4325724);
        constructionCosts.put("Porto Moniz", 4325724);
        constructionCosts.put("Porto Santo", 4325724);
        constructionCosts.put("Ribeira Brava", 4325724);
        constructionCosts.put("Santa Cruz", 4325724);
        constructionCosts.put("Santana", 4325724);
        constructionCosts.put("São Vicente", 4325724);
        constructionCosts.put("Angra do Heroísmo", 4325724);
        constructionCosts.put("Calheta (Açores)", 4325724);
        constructionCosts.put("Corvo", 4325724);
        constructionCosts.put("Horta", 4325724);
        constructionCosts.put("Lagoa (Açores)", 4325724);
        constructionCosts.put("Lajes das Flores", 4325724);
        constructionCosts.put("Lajes do Pico", 4325724);
        constructionCosts.put("Madalena", 4325724);
        constructionCosts.put("Nordeste", 4325724);
        constructionCosts.put("Ponta Delgada", 4325724);
        constructionCosts.put("Povoação", 4325724);
        constructionCosts.put("Praia da Vitória", 4325724);
        constructionCosts.put("Ribeira Grande", 4325724);
        constructionCosts.put("Santa Cruz da Graciosa", 4325724);
        constructionCosts.put("Santa Cruz das Flores", 4325724);
        constructionCosts.put("São Roque do Pico", 4325724);
        constructionCosts.put("Velas", 4325724);
        constructionCosts.put("Vila do Porto", 4325724);
        constructionCosts.put("Vila Franca do Campo", 4325724);
        constructionCosts.put("Abrantes", 3781296);
        constructionCosts.put("Albufeira", 3781296);
        constructionCosts.put("Alenquer", 3781296);
        constructionCosts.put("Caldas da Rainha", 3781296);
        constructionCosts.put("Chaves", 3781296);
        constructionCosts.put("Covilhã", 3781296);
        constructionCosts.put("Elvas", 3781296);
        constructionCosts.put("Entroncamento", 3781296);
        constructionCosts.put("Espinho", 3781296);
        constructionCosts.put("Estremoz", 3781296);
        constructionCosts.put("Figueira da Foz", 3781296);
        constructionCosts.put("Guimarães", 3781296);
        constructionCosts.put("Ílhavo", 3781296);
        constructionCosts.put("Lagos", 3781296);
        constructionCosts.put("Loulé", 3781296);
        constructionCosts.put("Olhão", 3781296);
        constructionCosts.put("Palmela", 3781296);
        constructionCosts.put("Peniche", 3781296);
        constructionCosts.put("Peso da Régua", 3781296);
        constructionCosts.put("Portimão", 3781296);
        constructionCosts.put("Santiago do Cacém", 3781296);
        constructionCosts.put("São João da Madeira", 3781296);
        constructionCosts.put("Sesimbra", 3781296);
        constructionCosts.put("Silves", 3781296);
        constructionCosts.put("Sines", 3781296);
        constructionCosts.put("Tomar", 3781296);
        constructionCosts.put("Torres Novas", 3781296);
        constructionCosts.put("Torres Vedras", 3781296);
        constructionCosts.put("Vila Real de Santo António", 3781296);
        constructionCosts.put("Vizela", 3781296);
    }

    /**
     * List with all the cities.
     */
    private Vector<City> cities = new Vector<>();
    private Vector<Vector<Double>> distances = new Vector<>();

    public void setCities(Vector<City> cities){
        this.cities = cities;
    }

    public void setDistances(Vector<Vector<Double>> distances) {
        this.distances = distances;
    }

    public Vector<City> getCities() {
        return cities;
    }

    public void addCity(City city){
        cities.add(city);
    }

    public Vector<Vector<Double>> getDistances() {
        return distances;
    }
}
