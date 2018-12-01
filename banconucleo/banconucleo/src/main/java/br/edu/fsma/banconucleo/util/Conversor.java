package br.edu.fsma.banconucleo.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Conversor {
	public static LocalDate conversorData(Date data) {
		return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
