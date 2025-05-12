package com.ilija.dto;

import java.time.LocalDate;

public class IzvestajDTO extends DokumentDTO {
	
	    private String tip;
	    private String sadrzaj;
		public String getTip() {
			return tip;
		}
		public void setTip(String tip) {
			this.tip = tip;
		}
		public String getSadrzaj() {
			return sadrzaj;
		}
		public void setSadrzaj(String sadrzaj) {
			this.sadrzaj = sadrzaj;
		}
	    
	    

}
