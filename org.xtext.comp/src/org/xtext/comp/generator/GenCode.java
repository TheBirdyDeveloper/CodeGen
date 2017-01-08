package org.xtext.comp.generator;

import java.util.*;

public class GenCode{

	private String m_resultatCode;
	private GenTable m_table;
	private HashMap<String, Etiquette> m_listEtiq;

	public GenCode(GenTable table) {
		m_table = table;
		m_resultatCode = "";
		//remplir la liste des etiq
		makeGenTable();
	}

	public GenCode(){
		
		m_listEtiq = new HashMap<String,Etiquette>();
		
		
		m_resultatCode = "";

		//Instructions de base
		List<Instr> listInstr0 = new LinkedList<Instr>();
		//listInstr0.add(new InstrNop("","","",""));
		//listInstr0.add(new InstrIf("L1","","L2","L3"));


		//If condition
		List<Instr> listInstr1 = new LinkedList<Instr>();
		listInstr1.add(new InstrOr("OR","","A","B"));

		//If Vrai
		List<Instr> listInstr2 = new LinkedList<Instr>();
		listInstr2.add(new InstrNop("","","",""));
		//listInstr2.add(new InstrIf("L4","","L5","L6"));

		//If Faux
		List<Instr> listInstr3 = new LinkedList<Instr>();
		listInstr3.add(new InstrNop("","","",""));
		
		List<Instr> listInstr4 = new LinkedList<Instr>();
		listInstr4.add(new InstrOr("OR","","C","D"));
		
		List<Instr> listInstr5 = new LinkedList<Instr>();
		listInstr5.add(new InstrNop("","","",""));
		
		List<Instr> listInstr6 = new LinkedList<Instr>();
		listInstr6.add(new InstrNop("","","",""));


		m_listEtiq.put("L0",new Etiquette(listInstr0));
		m_listEtiq.put("L1",new Etiquette(listInstr1));
		m_listEtiq.put("L2",new Etiquette(listInstr2));
		m_listEtiq.put("L3",new Etiquette(listInstr3));
		m_listEtiq.put("L4",new Etiquette(listInstr4));
		m_listEtiq.put("L5",new Etiquette(listInstr5));
		m_listEtiq.put("L6",new Etiquette(listInstr6));

		System.out.println(m_listEtiq.toString()+"\n");
		makeGenTable();
	}

	private void makeGenTable(){

		Etiquette currentEtiq = m_listEtiq.get("L0");

		Iterator<Instr> itInstr = currentEtiq.get().iterator();

		while(itInstr.hasNext()){//Visite des instructions

			Instr currentInstr = itInstr.next();

			System.out.print(makeObject(currentInstr, 0));

		}
	}
	
	private String makeGenTable(Etiquette etiq, int index){
		String result="";

		Iterator<Instr> itInstr = etiq.get().iterator();

		while(itInstr.hasNext()){//Visite des instructions

			Instr currentInstr = itInstr.next();

			result += makeObject(currentInstr, index);

		}
		
		return result;
	}

	private String makeObject(Instr instr, int index){
		if(instr instanceof InstrNop){
			return makeNop();
		}

		if(instr instanceof InstrIf){
			return makeIf((InstrIf)instr, index);
		}
		
		if(instr instanceof InstrOr){
			return makeOr((InstrOr)instr);
		}
		
		return "NULL";
	}

	private String makeNop(){
		return "nop;\n";
	}
	
	private String makeOr(InstrOr instr){
		return "("+/*makeGenTable(m_listEtiq.get(instr.getExpr1())*/instr.getExpr1()+" Or "+instr.getExpr2()+")";
	}

	private String makeIf(InstrIf instr, int index){
		String result=index("", index);
		result+="if <"+makeGenTable(m_listEtiq.get(instr.getCond()), 0)+ "> then\n";
		result=index(result, index+1);
		result+=makeGenTable(m_listEtiq.get(instr.getSiVrai()), index+1)+"\n";
		result=index(result, index);
		result+="else\n";
		result=index(result, index+1);
		result+=makeGenTable(m_listEtiq.get(instr.getSiFaux()), index+1);
		
		return result;
	}
	
	private String index(String text, int index){
		for(int i=1; i<=index; i++){
		text += "   ";}
		
		return text;
	}

	public void writeCode(){
		System.out.println(m_resultatCode);
	}

	public static void main(String args[]){
		GenCode test = new GenCode();
		test.writeCode();
	}
}
