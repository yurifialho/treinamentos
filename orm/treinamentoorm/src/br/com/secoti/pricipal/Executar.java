package br.com.secoti.pricipal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import br.com.secoti.models.Conta;
import br.com.secoti.models.Lancamento;
import br.com.secoti.models.Usuario;
import br.com.secoti.util.hibernate.HibernateUtil;

public class Executar {
	public static void main(String[] args) {
		Session s = HibernateUtil.getSession();
		s.beginTransaction();
		Usuario ui = new Usuario();
			ui.setNome("Yuri Fialho");
		Conta c1 = new Conta();
			c1.setDescricao("CARTAO");
			c1.setUsuario(ui);
			c1.setLancamentos(getLancamentos(c1));
		Conta c2 = new Conta();
			c2.setDescricao("CORRENTE");
			c2.setUsuario(ui);
			c2.setLancamentos(getLancamentos(c2));
		List<Conta> contas = new ArrayList<Conta>();
			contas.add(c1);
			contas.add(c2);
		ui.setContas(contas);
		s.save(ui);
		s.getTransaction().commit();
		Usuario u = (Usuario) s.load(Usuario.class, ui.getId());
			System.out.println(" ---------------------------------- ");
			System.out.println(u.getId() + " - " + u.getNome());
		for(Conta conta : u.getContas()) {
			System.out.println("------ " + conta.getId() + " | " + conta.getDescricao() + " -------");
			for(Lancamento l : conta.getLancamentos()) {
				System.out.println("#" + l.getId() + " - " + l.getValor() + " - " + l.getData());
			}
		}
	}
	
	public static List<Lancamento> getLancamentos(Conta c) {
		List<Lancamento> lancamentos = new ArrayList<Lancamento>();
		for(int i=0; i<10; i++) {
			Lancamento l = new Lancamento();
				l.setData(new Date());
				l.setValor(Math.random() * 74.9);
				l.setConta(c);
				lancamentos.add(l);
		}
		return lancamentos;
	}
}
