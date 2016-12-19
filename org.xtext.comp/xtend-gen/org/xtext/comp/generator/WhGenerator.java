/**
 * generated by Xtext 2.10.0
 */
package org.xtext.comp.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.xtext.comp.generator.GenTable;
import org.xtext.comp.generator.Instr;
import org.xtext.comp.generator.InstrNop;

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
@SuppressWarnings("all")
public class WhGenerator extends AbstractGenerator {
  private GenTable genTable;
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
  }
  
  public void doGenerate(final GenTable genTable, final IFileSystemAccess2 fsa, final String outputName) {
    this.genTable = genTable;
    String _genCode3Adr = this.genCode3Adr(genTable.listCode3Adr);
    fsa.generateFile(outputName, _genCode3Adr);
  }
  
  public String genCode3Adr(final HashMap<String, List<Instr>> map) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Set<String> _keySet = map.keySet();
      for(final String key : _keySet) {
        _builder.append("function ");
        _builder.append(key, "");
        _builder.append("()");
        _builder.newLineIfNotEmpty();
        {
          List<Instr> _get = map.get(key);
          for(final Instr instr : _get) {
            {
              if ((instr instanceof InstrNop)) {
              }
            }
          }
        }
        _builder.append("end");
        _builder.newLine();
      }
    }
    return _builder.toString();
  }
}
