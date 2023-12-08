package fes.aragon.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class InicioController implements Initializable {

    @FXML
    private ImageView imagen;

    @FXML
    private Pane panel;

    @FXML 
    void eventoRaton(MouseEvent event) {
    	
    	System.out.println((int) event.getX() + " " + (int) event.getY());
    	llenar((int) event.getX(), (int) event.getY(), 3500, "inicio"); 
    }

    private PixelReader pixel; 	
    
    private WritableImage escribir; 	
    
    private PixelWriter pixelEscribir;  
    
    public void llenar(int x, int y, int llamada, String lectura) { 		
    	if ((x < escribir.getWidth() && y < escribir.getHeight()) && (x >= 0 && y >= 0)) { 			
    		Color colores = pixel.getColor(x, y);  			
    		  			
    		if (llamada <= 0) { 				
    			return; 			
    		} else { 				  
    			pixelEscribir.setColor(x, y, Color.ORANGE); 				
    			if (colores.equals(new Color(1, 1, 1, 1))) {					 					
    				llenar(x + 1, y, --llamada, "der"); 					
    				llenar(x - 1, y, --llamada, "izq"); 					
    				llenar(x, y + 1, --llamada, "abajo"); 					
    				llenar(x, y - 1, --llamada, "arriba"); 				
    			} else { 					
    				return; 				
    			} 			
    		} 		
    	} 	
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		panel.setMaxWidth(imagen.getImage().getWidth()); 		
		panel.setPrefHeight(imagen.getImage().getHeight()); 	
		pixel = imagen.getImage().getPixelReader(); 
		
		escribir = new WritableImage((int) imagen.getImage().getWidth(), (int) imagen.getImage().getHeight()); 		
		pixelEscribir = escribir.getPixelWriter(); 		
		
		for (int x = 0; x < imagen.getImage().getWidth(); x++) { 			
			for (int y = 0; y < imagen.getImage().getHeight(); y++) { 				
				Color color = pixel.getColor(x, y); 				
				pixelEscribir.setColor(x, y, color); 			
				} 		
			} 	
		
		imagen.setImage(escribir); 		
		pixel = imagen.getImage().getPixelReader();
		
	}

}
