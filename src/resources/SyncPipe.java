package resources;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import entities.AllJsonData;
import entities.Pokemon;

class SyncPipe implements Runnable {
	private Boolean flag = false;

	public SyncPipe(InputStream istrm, OutputStream ostrm) {
		istrm_ = istrm;
		// ostrm_ = ostrm;
	}

	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(istrm_, "UTF-8"));
			String str = new String();
			while ((str = br.readLine()) != null) {
				AnalizeString(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void AnalizeString(String str) {
		Pokemon temp = CheckWhatWasCaught(str);
		if (str.contains("There is no"))
			DPSUtils.log("The Pokemon " + temp.getDispalyName() + " was not found at the location.");
		if (str.contains("We caught a")) {
			temp.updateLableValue();
			DPSUtils.log("The Pokemon " + temp.getDispalyName() + " was caught.");
		}
		if (str.contains("got away.")) {
			DPSUtils.log("The Pokemon " + temp.getDispalyName() + " got away.");
		}
		if (str.contains("We have") && str.contains("berries left")) {
			Pattern p = Pattern.compile("\\d+");
			Matcher m = p.matcher(str);
			String s = "";
			for (int x = 0; x < 6 && m.find(); x++) {
				if (x == 3) {
					s += "We have " + m.group() + " Pokeballs, ";
					if (Integer.parseInt(m.group()) == 0) {
						DPSUtils.stopBot();
						this.flag=true;
					}
				} else if (x == 4) {
					s += "" + m.group() + " berries, ";
				} else if (x == 5) {
					s += "" + m.group() + " Pokemons";
				}
			}
			DPSUtils.log(s);
		}
		if(str.contains("Got into the fight without any Pokeballs")) {
			flag=true;
		}

		if(flag==true) {
			DPSUtils.log("No more Pokeballs left, stoping bot");
			flag=false;
		}
	}

	private Pokemon CheckWhatWasCaught(String str) {
		Pokemon pokemonType = null;
		for (Pokemon type : AllJsonData.getPokelist()) {
			if (StringUtils.containsIgnoreCase(str, type.getName())
					|| StringUtils.containsIgnoreCase(str, type.getDispalyName())) {
				pokemonType = type;
				break;
			}
		}
		return pokemonType;
	}

	// private final OutputStream ostrm_;
	private final InputStream istrm_;
}