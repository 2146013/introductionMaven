package edu.escuelaing.AREP;

import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.*;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getPort());
        get("/results", (req, res) -> resultsPage(req, res));
        get("/facadealpha","application/json",(req, res) -> facadeAlpha(req, res));
        get("/iexapis","application/json",(req, res) -> iexapis(req, res));
    }
    private  static Memorycache memorycache = new Memorycache();
    private static String facadeAlpha(Request req, Response res) {
        String response ="None";
        try {
            response= HttpStockService.getService().getStockJSON(req);
        } catch (IOException e) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE,null,e);
        }
        if(memorycache.getalfaCache(req.url()).equals("none") ){
            memorycache.addalfaCache(req.url(),response);
            return response;
        }
        return memorycache.getalfaCache(req.url());

    }

    private static String iexapis(Request req, Response res) {
        String response ="None";
        try {
            response= HttpStockService.getService().getStockInfoAsJSONiex(req);
        } catch (IOException e) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE,null,e);
        }
        return response;
    }


    private static String resultsPage(Request req, Response res) {
        return req.queryParams("firstname") + " " +
                req.queryParams("lastname");
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
    private static String getStockInfo(Request req,Response res){
        res.type("application/json");
        String responseStr = "none";
        try{
            HttpStockService stockService = HttpStockService.createService();
            responseStr = stockService.getStockJSON(req);
        }
        catch (IOException ex){
            Logger.getLogger(App.class.getName()).log(Level.SEVERE,null,ex);
        }
        return responseStr;
    }

}