package edu.escuelaing.AREP;


public class IEXCloudHttpStockService extends HttpStockService{
    @Override
    public String getURL(String time,String stock) {
        return "https://cloud.iexapis.com/stable/stock/market/batch/time_series?symbols="+stock+"&types=quote,chart&range="+time+"&token=pk_04b714a99e6d4d6394657768c1274360";

    }
}