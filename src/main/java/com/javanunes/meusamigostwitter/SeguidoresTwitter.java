/*
* Esse é um código mostrando de forma simples, didática, sem usar nenhum padrão profissional e enrolações tradicionais
* do Java para consumir APIs, voltado a quem está acostumado a usar curl_setopt() no PHP mas que não sabe nem por onde
* começar no Java. Deixei o código bem ridículo para os padrões Java por entender que é de códigos rídiculos que se
* aprende a fazer códigos complexos e bonitinhos. Por favor, para tudo dar certo o projeto deve usar o pom.xml que eu
* fiz e de preferencia dentro do Netbeans. Por favor, não usem o eclipse pois ele é bagunçado, confuso e rudimentar demais embora seja o predileto
* dos programadores Javas narcisistas.
*/

package com.javanunes.meusamigostwitter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// Pacotes adicionados com adições externas no pom.xml, se você não
// adicionar fazer as adições desses pacotes pelo maven no pom.xml
// esses importas não irão funcionar.
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

/**
 * @author JavaNunes 
 */



public class SeguidoresTwitter {
    // URL da api do twitter para obser os primeiros seguidores do usuario id 49863285
    private static String urlTwitter="https://api.twitter.com/2/users/49863285/followers";
    
    // Token fornecido pelo twitter em https://developer.twitter.com/en
    private static String tokenTwitter="<Substitua isso pelo token recebido do Twitter objetido por você, o meu eu não posso mostrar, né?>";
     
    public static String fazerPedidoReceberRespostaAPITwitter(String url){
        try {
                URL obj = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("User-Agent", "PornoChrome");
                conn.setRequestProperty("Accept", "application/json");

                // Passa o token feio no site do twitter no cabeçalho da soliictação http para o twitter nos liberar
                conn.setRequestProperty("authorization", "Bearer " + tokenTwitter);

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String jsonCodigoLinha;
                StringBuffer resposta = new StringBuffer();

                while ((jsonCodigoLinha = in.readLine()) != null) {
                        resposta.append(jsonCodigoLinha);
                }
                return resposta.toString();    
        } 
        catch(Exception e){
                return "Erro ao obter lixo JSON do twitter: "+e;   
        }
    }   
       
    public static void decodificarJSONRecebidoListarAmigos(String lixo){
        try{
            JSONObject jsonObject;
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(lixo);
            System.out.println("================== LISTA AMIGOS DO TWITTER ======================");
            
            // Obter um a um o campo name do JSON recebido para listar
            // para listar o nome dos seguidores do id 49863285
            for (JsonNode nome : rootNode.findValues("name")) {
                System.out.println(nome);
            } 
        }
        catch(Exception e){
            System.out.println("Erro ao decodificar JSON recebido do Twitter API: "+e);
        }
    }
    
    
    
    
    /* ENTRY POINT ----------------------------------------------------------------*/
    public static void main(String... args){
        
        // Entra em contato com a API do twitter a provocando para ter resposta
        String lixoJSON = fazerPedidoReceberRespostaAPITwitter(urlTwitter);
        
        // Obtem a resposta JSON da API do Twitter provocada a decodifica e lista o nome dos seguidores
        decodificarJSONRecebidoListarAmigos(lixoJSON);
        
    }
    
        
        
        
        
        
}
