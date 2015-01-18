package org.polytech.nantes.monopoly.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.polytech.nantes.monopoly.manager.Joueur;
import org.polytech.nantes.monopoly.manager.MainManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ScoreBoard {
		
		/**
		 * Méthode pour récupérer l'index d'un joueurs par son nom uniquement
		 * @param name, nom du joueur recherché
		 * @param joueurs, tableau de joueurs
		 * @return position du joueur, ou -1 si non trouvé
		 */
		private static int indexOf(String name, List<Joueur> list) {
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getNom().equals(name))
					return i;
			}
			
			return -1;
		}
		
		/**
		 * Méthode pour chercher si le joueur est présent dans le tableau passé en paramètre 
		 * @param name, nom du joueur recherché
		 * @param joueurs, tableau de joueurs
		 * @return boolean si le joueur est bien dans le tableau ou pas
		 */
		private static boolean contains(String name, List<Joueur> list) {
			for(Joueur j : list) {
				if(j.getNom().equals(name))
					return true;
			}
			
			return false;
		}
		
		@SuppressWarnings("unchecked")
		/**
		 * Ajout d'une partie dans le fichier des scores
		 * @param joueurs, tableau de Joueur avec le gagnant en première position, les autres à la suite
		 * @return réussite ou echec de l'opération d'écriture
		 */
		public static boolean addScore(List<Joueur> list) {
			JSONParser parser = new JSONParser();
			
			try {
				boolean[] registeredPlayer = new boolean[list.size()];
				
				File scoreFile = new File("score.txt");
				if(!scoreFile.exists()) {
					scoreFile.createNewFile();
					FileWriter file = new FileWriter(scoreFile);
					file.write("{\"players\":[], \"details\":[]}");
					file.flush();
					file.close();
				}

				Object obj = parser.parse(new FileReader("score.txt"));
		 
				JSONObject jsonObject = (JSONObject) obj;
		 
				JSONArray players = (JSONArray) jsonObject.get("players");
				Iterator<JSONObject> iterator = players.iterator();
				while (iterator.hasNext()) {
					JSONObject o = (JSONObject) iterator.next();
			 		if(o.get("name").equals(list.get(0).getNom())) {
						o.put("score", (Long)o.get("score")+1);
					
						o.put("nbgame", (Long)o.get("nbgame")+1);
					
						registeredPlayer[ScoreBoard.indexOf((String)o.get("name"), list)] = true;
					}   
				}
				
				for(int i = 0; i < list.size(); i++) {
					if(!registeredPlayer[i]) {
						JSONObject pl = new JSONObject();
						pl.put("name", list.get(i).getNom());
						int score = 0;
						if(i == 0) score++;
						pl.put("score", score);
						pl.put("nbgame", 1);
						players.add(pl);
					}
				}
				
				
				boolean[] player = new boolean[list.size()];
				boolean[] playerOpponent = new boolean[list.size()];
				JSONArray details = (JSONArray) jsonObject.get("details");
				iterator = details.iterator();
				while(iterator.hasNext()) {
					JSONObject o = (JSONObject) iterator.next();
					if( o.get("playername").equals(list.get(0).getNom()) && ScoreBoard.contains((String)o.get("opponentname"), list) ) {
						o.put("playernbwin", (Long)o.get("playernbwin")+1);
						player[ScoreBoard.indexOf((String) o.get("opponentname"), list)] = true;
					}
					if( o.get("opponentname").equals(list.get(0).getNom()) && ScoreBoard.contains((String)o.get("playername"), list) ) {
						o.put("playernbdefeat", (Long)o.get("playernbdefeat")+1);
						playerOpponent[ScoreBoard.indexOf((String) o.get("playername"), list)] = true;
					}
				}
				
				Iterator<Joueur> joueursIte = list.iterator();
				joueursIte.next();
				while(joueursIte.hasNext()) {
					JSONObject detail = new JSONObject();
					Joueur p = joueursIte.next();
					if(!player[list.indexOf(p)]) {
						detail.put("playername", p.getNom());
						detail.put("opponentname", list.get(0).getNom());
						detail.put("playernbdefeat", 1);
						detail.put("playernbwin", 0);
						details.add(detail);
					}
					
					if(!playerOpponent[list.indexOf(p)]) {
						detail = new JSONObject();
						detail.put("playername", list.get(0).getNom());
						detail.put("opponentname", p.getNom());
						detail.put("playernbdefeat", 0);
						detail.put("playernbwin", 1);
						details.add(detail);
	 				}
				}
				
				FileWriter file = new FileWriter(scoreFile);
				file.write(jsonObject.toJSONString());
				file.flush();
				file.close();
				
				return true;
		 
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			return false;		     
		}
		
		
		public static class TestScoreBoard {
			public static void main(String args[]) {
				String j[] = new String[4];
				j[0] = "Paul";
				j[1] = "Jean";
				
				MainManager plateau = new MainManager(10, j);
				
				Vector<Joueur> listeJoueurs = new Vector<Joueur>();				
				listeJoueurs.add(plateau.getJoueurs()[0]);
				listeJoueurs.add(plateau.getJoueurs()[1]);
				
				ScoreBoard.addScore(listeJoueurs);				
			}
		}
}