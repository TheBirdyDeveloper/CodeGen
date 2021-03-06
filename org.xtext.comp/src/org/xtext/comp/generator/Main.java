/*
 * generated by Xtext 2.10.0
 */
package org.xtext.comp.generator;

import java.util.List;

import org.apache.commons.cli.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.generator.GeneratorContext;
import org.eclipse.xtext.generator.GeneratorDelegate;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.xtext.comp.WhStandaloneSetup;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

public class Main {
	
	private static final String DEFAULT_NAME = "f.lua";
	private static String inputFileName;
	private static String outputFileName;
	
	private static final CommandLineParser parser = new GnuParser();
	private static final HelpFormatter formatter = new HelpFormatter();
	private static final Options options  = new Options();
	private static CommandLine cmd;
	
	public static void main(String[] args) {
		outputFileName = DEFAULT_NAME;
		
		Injector injector = new WhStandaloneSetup().createInjectorAndDoEMFRegistration();
		Main main = injector.getInstance(Main.class);
		
		int error = optionManager(args);
		if(error != 0) return;
		
		main.runGenerator();
	}

	protected void runGenerator() {
		// Load the resource
		ResourceSet set = resourceSetProvider.get();
		
		Resource resource;
		try{
		resource = set.getResource(URI.createFileURI(inputFileName), true);
		}
		catch (Exception e){
			throw new Error("Le fichier sp�cifi� est introuvable : "+inputFileName);
		}

		// Validate the resource
		List<Issue> list = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
		if (!list.isEmpty()) {
			for (Issue issue : list) {
				System.err.println(issue);
			}
			return;
		}


		SymTable symtable = new SymTable(resource);
		GenTable gentable = new GenTable(symtable);
		
		// Configure and start the generator
		fileAccess.setOutputPath("./");

		GeneratorContext context = new GeneratorContext();
		context.setCancelIndicator(CancelIndicator.NullImpl);
		
		WhGenerator wh = new WhGenerator();
		wh.doGenerate(gentable, fileAccess, outputFileName);
	
		//System.out.println(gentable.nomsToString());
		
		/*System.out.println("\nTable des Symboles :\n"+symtable.toStringSymboles());
		System.out.println("\nTable des Appels :\n"+symtable.toStringAppels());
		System.out.println("\nGestion des erreurs :\n");symtable.toStringError();
		
		/*System.out.println("\n-------------- GENCODE --------------");
		System.out.println(gentable.nomsToString());*/
		
		
	}
	
	private static void printMan(){
		String separation = "\n\n";
		String man = "";
		String NAME = "whc - While Compiler";
		String SYNOPSIS = "whc -[io] file";
		String DESC = "whc permet de compiler un fichier While en Lua";
		String OPT = "\t-i, --input file\n\t\t\tnom du fichier d'entr�e\n"
				+ "\t-o, --output file\n\t\t\tnom du fichier de sortie\n"
				+ "\t-h, --help\n\t\t\taffiche l'aide";
		String BUGS = "";
		String SEE = "whc";
		String AUTHORS = "�crit par Cl�ment Guihaire - Pierre Marais - Mathieu Menuet - Marc Perret - Olivier Peurichard";
		
		man = "NOM\n\t"+NAME+separation+"SYNOPSIS\n\t"+SYNOPSIS+separation+"DESCRIPTION\n\t"+DESC+separation+"OPTIONS\n\t"+OPT+separation+"BUGS\n\t"
				+BUGS+separation+"AUTEURS\n\t"+AUTHORS+separation+"VOIR AUSSI\n\t"+SEE;
		System.out.println(man);
	}
	
	/**
	 * 
	 * @param args arguments du Main
	 * @return -1 si erreur d'arguments; 0 si pas d'erreur
	 */
	private static int optionManager(String[] args){
		
		
		Option help = new Option("h","help",false,"help");
		options.addOption(help);
		Option output = new Option("o","output",true,"output file");
		options.addOption(output);
		Option input = new Option("i","input",true,"input file");
		options.addOption(input);		
		
		
		 try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            displayError(e.getMessage());
            return -1;
        }
		 
		boolean helpParam = cmd.hasOption("help");
		if(helpParam){
			printMan();
			System.exit(0);
		}
		
		boolean inputFile = cmd.hasOption("input");
		if(!inputFile){
			displayError("Missing option : i");
			return -1;
		}
		
		inputFileName = cmd.getOptionValue("input");
		
		String outputFile = cmd.getOptionValue("output");
		if(outputFile != null ){
			outputFileName = outputFile;
		}
		
		
		return 0;
	}
	
	private static void displayError(String msg){
		System.out.println(msg);
		formatter.printHelp("whc", options);
        System.exit(1);
	}
	

	@Inject
	private Provider<ResourceSet> resourceSetProvider;

	@Inject
	private IResourceValidator validator;

	@Inject
	private GeneratorDelegate generator;

	@Inject 
	private JavaIoFileSystemAccess fileAccess;
}