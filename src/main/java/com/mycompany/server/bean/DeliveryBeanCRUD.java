/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.bean;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.mycompany.server.Entity.Address;
import com.mycompany.server.Entity.Client;
import com.mycompany.server.Entity.Request;
import com.mycompany.server.LAFF.Box;
import com.mycompany.server.LAFF.Container;
import com.mycompany.server.LAFF.Packager;
import com.mycompany.server.ejb.AddressRemote;
import com.mycompany.server.ejb.ClientRemote;
import com.mycompany.server.ejb.RequestRemote;

import com.mycompany.server.tsp.City;
import com.mycompany.server.tsp.DistanceArray;
import com.mycompany.server.tsp.GA;
import com.mycompany.server.tsp.Population;
import com.mycompany.server.tsp.TourManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import javax.ejb.EJB;
/**
 *
 * @author Alimkhanov Ernur, Stakhan Temirlan, Serikuly Orynbek
 */
@Stateless
@XmlAccessorType(XmlAccessType.FIELD)
@WebService(serviceName="DeliveryServer")
public class DeliveryBeanCRUD implements DeliveryBeanRemote{
    
    @EJB
    ClientRemote clientBean;
    
    @EJB
    RequestRemote requestBean;
    
    @EJB
    AddressRemote addressBean;
    
    
    public DeliveryBeanCRUD(){
        
    }
    
   @PersistenceContext(unitName="com.mycompany_Server_war_1.0-SNAPSHOTPU")
   private EntityManager em;    



    @Override
    @WebMethod(operationName="getLocation")
    public String getLocation(String address) {
        String out2 = "";    
        
            GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyDgFJiED3lOMBvZHDxDW4699QVW3ucnPNU");
            GeocodingResult[] results;
        try {
            results =  GeocodingApi.geocode(context,address).await();
            out2 = results[0].geometry.location.toString();
        } catch (Exception ex) {
            Logger.getLogger(DeliveryBeanCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        return out2;
    }

    @Override
    @WebMethod(operationName="getShortestHamiltonianCycle")
    public List<Integer> getShortestHamiltonianCycle(int[][] dist) {
            int n = dist.length;
            int[][] dp = new int[1 << n][n];
            for (int[] d : dp)
                Arrays.fill(d, Integer.MAX_VALUE / 2);
            dp[1][0] = 0;
            for (int mask = 1; mask < 1 << n; mask += 2) {
                for (int i = 1; i < n; i++) {
                    if ((mask & 1 << i) != 0) {
                        for (int j = 0; j < n; j++) {
                            if ((mask & 1 << j) != 0) {
                                dp[mask][i] = Math.min(dp[mask][i], dp[mask ^ (1 << i)][j] + dist[j][i]);
                            }
                        }
                    }
                }
            }
            int res = Integer.MAX_VALUE;
            for (int i = 1; i < n; i++) {
                res = Math.min(res, dp[(1 << n) - 1][i] + dist[i][0]);
            }

    // reconstruct path
            int cur = (1 << n) - 1;
            int[] order = new int[n];
            int last = 0;
            for (int i = n - 1; i >= 1; i--) {
                int bj = -1;
                for (int j = 1; j < n; j++) {
                    if ((cur & 1 << j) != 0 && (bj == -1 || dp[cur][bj] + dist[bj][last] > dp[cur][j] + dist[j][last])) {
                        bj = j;
                    }
                }
            order[i] = bj;
            cur ^= 1 << bj;
            last = bj;
            }
    
            Integer [] newArray = new Integer[order.length];
            for(int i = 0; i<order.length;i++){
                newArray[i] = Integer.valueOf(order[i]);
            }
    return Arrays.asList(newArray);
    }

    @Override
    @WebMethod(operationName="getNavigationCycle")
    public List<String> getNavigationCycle(List<String> add) {
        
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyDgFJiED3lOMBvZHDxDW4699QVW3ucnPNU");
        
        List<LatLng> locationList = new ArrayList<LatLng>();
        int latLngmat [][]  = new int[add.size()][add.size()];
        
        for(String test : add){  
            locationList.add(getLocationLatLng(test));
        }
        
        int i =0;
        
        
        
        for(LatLng test2 : locationList){
            int j = 0;
            for(LatLng test3 : locationList){
                try {
                    DirectionsResult result = DirectionsApi.newRequest(context)
                    .origin(test2)
                    .destination(test3)
                    .mode(TravelMode.DRIVING)
                    .region("kz").await();
                    
                    long route = result.routes[0].legs[0].distance.inMeters;
                    
                    latLngmat[i][j] = (int) route;
                    
                } catch (Exception ex) {
                    Logger.getLogger(DeliveryBeanCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                j++;
            }
            i++;
        }
        
        List<Integer> result = getShortestHamiltonianCycle(latLngmat);
        
        while(result.get(0) != 0){
            result.add(result.get(0));
            result.remove(0);
        }
        
        List<String> finalResult = new ArrayList();
        for(Integer index:result){
            finalResult.add(add.get(index));
        }
        
        return finalResult;
    }

    @Override
    @WebMethod(operationName="getLocationLatLng")
    public LatLng getLocationLatLng(String address) {
        
        
                GeoApiContext context = new GeoApiContext().setQueryRateLimit(3)
                .setConnectTimeout(1, TimeUnit.SECONDS)
                .setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS)
                    .setApiKey("AIzaSyDgFJiED3lOMBvZHDxDW4699QVW3ucnPNU");
        
        LatLng location = null;
        
        GeocodingResult[] results;
        try {
            results =  GeocodingApi.geocode(context,address).await();
            location = results[0].geometry.location;
            
        } catch (Exception ex) {
            Logger.getLogger(DeliveryBeanCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }            
        
        
       
        return location;
    }

    /**
     *
     * @param add
     * @param key
     * @return
     * @throws Exception
     */
    @Override
    @WebMethod(operationName="getNavigationGA")
    public List<String> getNavigationGA(List<String> add, String key) throws Exception {
        
        
        boolean status = clientBean.checkKey(key);
        
        if(status == true){
       /*     Client client = clientBean.getClientBYKey(key);
            
            
            List<Address> addList = new ArrayList();
            
            requestBean.addRequest(client);  
            
            for(String i:add){
                addressBean.addAddress(requestBean.getAllRequestsOfClient(client).get(0),i);
            }*/
            
                     
            
            
       List<String> result2 = new ArrayList();
        
    
            for(int i=0;i<add.size();i++){
                City city = new City(i, i,add.get(i),i);
                TourManager.addCity(city);
            }
            
                    double latLngmat [][]  = new double[TourManager.numberOfCities()][TourManager.numberOfCities()];
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyDgFJiED3lOMBvZHDxDW4699QVW3ucnPNU");
        for(int i = 0;i< TourManager.numberOfCities();i++){
            for(int j = 0; j< TourManager.numberOfCities();j++){
                    DateTime now = new DateTime();
                    DirectionsResult result = DirectionsApi.newRequest(context)
                    .origin(TourManager.getCity(i).getName())
                    .destination(TourManager.getCity(j).getName())
                    .mode(TravelMode.DRIVING)
                    .departureTime(now)
                    .region("kz").await();
                    
                    double res = result.routes[0].legs[0].distance.inMeters;
                    System.out.println(res);

                    latLngmat[i][j] = res;
                    
            }
        }
        
        DistanceArray.latLngmat = latLngmat;
        
        Population pop = new Population(50, true);
        //System.out.println("Initial distance: " + pop.getFittest().getDistance());

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 100; i++) {
            pop = GA.evolvePopulation(pop);
        }
        
        for(int i=0;i<add.size();i++){
            result2.add(pop.getFittest().getCity(i).getName());
        }        
        
        while(result2.get(0) != add.get(0)){
            result2.add(result2.get(0));
            result2.remove(0);
        }
        Arrays.fill(DistanceArray.latLngmat, null);
            System.out.println(result2);
        return result2;
        }
        else{
            return null;
        }
       
    }

    @Override
    @WebMethod(operationName="getPacking")
    public String getPacking(int[][] prod, List<Integer> con, List<String> add) {
        

        List<Box> containers = new ArrayList<Box>();
        containers.add(new Box(con.get(0), con.get(1), con.get(2)));
	Packager packager = new Packager(containers);
		
        List<Box> products = new ArrayList<Box>();
        
        for(int i=0;i<prod.length;i++){ 
            products.add(new Box("Product "+add.get(i)+prod[i][0],prod[i][1],prod[i][2],prod[i][3]));
        }
        
		
	Container fits = packager.pack(products);
                
        
        if(fits!=null){ 
            return fits.printLevels();
        }
        else return "No solution!";
    }
    
    @Override
    @WebMethod(operationName="getPackingIP")
    public String getPackingIP(int[][] prod, List<Integer> con, List<String> add) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            JSONObject json = new JSONObject();
            
            List<Box> products = new ArrayList<Box>();
            
            List<Map<String , String>> bins  = new ArrayList<Map<String,String>>();
            
            Map<String, String> container1 = new HashMap<String, String>();
            container1.put("w", con.get(0).toString());
            container1.put("d", con.get(1).toString());
            container1.put("h", con.get(2).toString());
            container1.put("max_wg", "0");
            container1.put("id", "Container1");
            bins.add(container1);
            
            List<Map<String , String>> items  = new ArrayList<Map<String,String>>();
            Map<String, String> item = new HashMap<String, String>();
            for(int i=0;i<prod.length;i++){
                products.add(new Box("Product "+add.get(i)+prod[i][0],prod[i][1],prod[i][2],prod[i][3]));
                item.put("w", Integer.toString(prod[i][1]));
                item.put("d", Integer.toString(prod[i][2]));
                item.put("h", Integer.toString(prod[i][3]));
                item.put("q", "1");
                item.put("vr", "1");
                item.put("wg", "0");
                item.put("id", Integer.toString(prod[i][0]));
                items.add(item);
            }
            
            //SET PARAMETERS
            Map <String , String > params  = new HashMap <String , String >();
            params.put("images_background_color", "255,255,255");
            params.put("images_bin_border_color", "59,59,59");
            params.put("images_bin_fill_color", "230,230,230");
            params.put("images_item_border_color", "214,79,79");
            params.put("images_item_fill_color", "177,14,14");
            params.put("images_item_back_border_color", "215,103,103");
            params.put("images_sbs_last_item_fill_color", "99,93,93");
            params.put("images_sbs_last_item_border_color", "145,133,133");
            params.put("images_width", "100");
            params.put("images_height", "100");
            params.put("images_source", "file");
            params.put("images_sbs", "1");
            params.put("stats", "1");
            params.put("item_coordinates", "1");
            params.put("images_complete", "1");
            params.put("images_separated", "1");
            
            //ADD ELEMENTS TO JSON
            json.put("username", "tstakhan");
            json.put("api_key", "2a52fda428eae81fc877ea587eefd05e");
            json.put("items", items);
            json.put("bins", bins);
            json.put("params", params);
            
            //CALL QUERY
            HttpURLConnection conn;
            URL addr = new URL("http://eu.api.3dbinpacking.com/packer/pack");
            conn = (HttpURLConnection) addr.openConnection();
            try {
                conn.setRequestMethod("POST");
            } catch (ProtocolException ex) {
                Logger.getLogger(DeliveryBeanCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
            conn.setDoOutput(true);
            conn.connect();
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
            
            // prepare POST body
            String query = "query=" + json.toString();
            
            osw.write(query);
            osw.flush();
            osw.close();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response_json = br.readLine();
            
            //DO SOMETHING COOL WITH THE RESPONSE
            //System.out.println(response_json);
            return response_json;
        } catch (MalformedURLException ex) {
            Logger.getLogger(DeliveryBeanCRUD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DeliveryBeanCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "No solution!";
    }
    
}
