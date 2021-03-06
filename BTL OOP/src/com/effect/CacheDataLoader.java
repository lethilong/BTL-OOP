package com.effect;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

import javax.imageio.ImageIO;



public class CacheDataLoader {
	private static CacheDataLoader instance;
	 private String framefile ="data/frame.txt";
	 private String animationfile ="data/animation.txt";
	 private String physmapfile = "data/phys_map1.txt";
	 private String soundfile = "data/sounds.txt";
	
	private Hashtable <String,FrameImage> frameImages; //cau truc hashtable luu du lieu theo ctruc ghi va luu
	private Hashtable <String, Animation> animations;
	private Hashtable<String, AudioClip> sounds;
	
	private int[][] phys_map;
	
	
	private CacheDataLoader() {
		
		
	}
	
	public static CacheDataLoader getInstance() {
		if(instance==null)
			instance =new CacheDataLoader();
		return instance;
		
		
	}
	public void LoadData() throws IOException{
		LoadFrame();
		LoadAnimation();
		LoadPhysMap();
		LoadSounds();
	}
	
	public AudioClip getSound(String name){
        return instance.sounds.get(name);
    }
	
	public int[][] getPhysicalMap(){
		
		return instance.phys_map;
	}
	
	
public void LoadPhysMap() throws IOException {
		
		FileReader fr= new FileReader(physmapfile); //filereader sau khi doc tra ve 1 thread
		BufferedReader br = new BufferedReader(fr); //bufreader doc tu luong
		
		String line=null;
		line= br.readLine();
		int numberOfRows = Integer.parseInt(line);
		line = br.readLine();
		int numberOfColumns = Integer.parseInt(line);
		
		instance.phys_map = new int[numberOfRows][numberOfColumns];
		for(int i=0; i<numberOfRows;i++) {
			line= br.readLine();
			String[] str= line.split(" ");
			for(int j=0; j<numberOfColumns;j++) {
				instance.phys_map[i][j]= Integer.parseInt(str[j]);
			}
		}
		for(int i = 0;i < numberOfRows;i++) {
            
            for(int j = 0;j<numberOfColumns;j++)
                System.out.print(" "+instance.phys_map[i][j]);
            
            System.out.println();
        }
        
        br.close();
	}
    public void LoadSounds() throws IOException{
    sounds = new Hashtable<String, AudioClip>();
    
    FileReader fr = new FileReader(soundfile);
    BufferedReader br = new BufferedReader(fr);
    
    String line = null;
    
    if(br.readLine()==null) { // no line = "" or something like that
        System.out.println("No data");
        throw new IOException();
    }
    else {
        
        fr = new FileReader(soundfile);
        br = new BufferedReader(fr);
        
        while((line = br.readLine()).equals(""));
        
        int n = Integer.parseInt(line);
        
        for(int i = 0;i < n; i ++){
            
            AudioClip audioClip = null;
            while((line = br.readLine()).equals(""));

            String[] str = line.split(" ");
            String name = str[0];
            
           // String path = str[1];

            try {
               audioClip =  Applet.newAudioClip(new URL("file","",str[1]));

            } catch (MalformedURLException ex) {}
            
            instance.sounds.put(name, audioClip);
        }
        
    }
    
    br.close();
    
    }

	public void LoadFrame() throws IOException{
		frameImages= new Hashtable<String, FrameImage>();
		FileReader fr= new FileReader(framefile);
		BufferedReader  br = new BufferedReader (fr);  //bat dau doc tu dong dau
		String line =null; //null khac ki tu rong hay chuoi rong, null la k co bat ki dong nao
		
		if(br.readLine()==null) {     //readline( ) tra ve 1 string
			System.out.println("No data");
			throw new IOException();
		}else {
			fr= new FileReader(framefile); //de dua con tro ve lai dau file
			br = new BufferedReader(fr); //de dua con tro ve lai dau file
			
			while ((line = br.readLine()).equals("")); //bo qua
			
			int n = Integer.parseInt(line); //lay so dau :158 de lay n tu file
			
			for(int i=0; i<n; i++) {
				FrameImage frame=new FrameImage();
				while ((line=br.readLine()).equals(""));
				frame.setName(line);
				
				while ((line=br.readLine()).equals(""));
				String[] str = line.split(" "); //split la ham chia line thanh mang cach boi dau "=". tu do string duoc chia thanh 2 phan tu
				String path =str[1]; //path o phan tu 2
				
				while ((line=br.readLine()).equals(""));
				str = line.split(" ");
				int x=Integer.parseInt(str[1]);
				
				while ((line=br.readLine()).equals(""));
				str = line.split(" ");
				int y=Integer.parseInt(str[1]);
				
				while ((line=br.readLine()).equals(""));
				str = line.split(" ");
				int w=Integer.parseInt(str[1]);
				
				while ((line=br.readLine()).equals(""));
				str = line.split(" ");
				int h=Integer.parseInt(str[1]);
				 
				BufferedImage imageData =ImageIO.read(new File(path));
				BufferedImage image = imageData.getSubimage(x, y, w, h);
				frame.setImage(image);
				instance.frameImages.put(frame.getName(),frame);
				
				
				
				
				
				
				
				
			}
			
		}
		br.close();
	}
	public FrameImage getFrameImage(String name) {
		FrameImage frameImage = new FrameImage(instance.frameImages.get(name));
		return frameImage;
	}
	public Animation getAnimation(String name) {
		Animation animation = new Animation(instance.animations.get(name));
		return animation;
	}
	public void LoadAnimation() throws IOException {
		animations= new Hashtable<String, Animation>();
		FileReader fr= new FileReader(animationfile);
		BufferedReader br =new BufferedReader(fr);
		String line = null;
		if( br.readLine()==null) {
			System.out.println("No Data");
			throw new IOException();			
			
		}else {
			fr =new FileReader(animationfile);
			br = new BufferedReader(fr);
			while((line= br.readLine()).equals(""));
			int n= Integer.parseInt(line);
			
			for(int i=0; i<n; i++) {
				Animation animation = new Animation();
				
				while((line=br.readLine()).equals(""));
				animation.setName(line);
				
				while((line=br.readLine()).equals(""));
				String[] str = line.split(" ");
				
				for(int j=0; j<str.length; j+=2)
					animation.add(getFrameImage(str[j]), Double.parseDouble(str[j+1]));
				
				instance.animations.put(animation.getName(), animation);
				
				
				
			}
		}
		br.close();
	}

}
