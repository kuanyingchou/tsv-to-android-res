import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.io.*;

class Convert {

  // key           |     en |   zh-TW
  //---------------+--------+--------
  // dialog_ok     |     OK |   
  // dialog_cancel | Cancel |  

  private static final String sep = "\t"; // TODO: escape, or the # of columns may be wrong!!!
  
  public static void main(String[] args) throws IOException {
    if(args.length != 1) {
      printUsage();
      return;
    }

    final String indent = "  ";
    final String prefix = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>";
    final String suffix = "</resources>";
    final String outputDir = "out";
    
    final String filename = args[0]; // "t.tsv";

    final List<List<String>> cells = getCells(readLines(filename));

    if(cells.isEmpty()) return; // nothing to do

    final List<String> columns = cells.get(0);

    for(int langIndex=1; langIndex<columns.size(); langIndex++) {
      final String lang = columns.get(langIndex); // en, zh, etc.

      final String fixedLang = fixLang(lang);

      final File dir = Paths.get(outputDir, String.format("values-%s", fixedLang)).toFile();
      if(!dir.exists()) {
        dir.mkdirs();
      }

      final File file = dir.toPath().resolve("strings.xml").toFile();
      if(file.exists()) {
        System.out.println("file exists: "+file); // TODO: merge file?
        break;
      }

      final BufferedWriter writer = new BufferedWriter(new FileWriter(file));
      // final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.err));

      // writer.write("writing to "+file); 
      // writer.newLine();

      writer.write(prefix);
      writer.newLine();

      for(int entryIndex=1; entryIndex<cells.size(); entryIndex++) {
        final List<String> entry = cells.get(entryIndex); 
        if(langIndex >= entry.size()) {
          System.err.println("invalid entry: "+cells.get(entryIndex));
          continue;
        }
        final String key = entry.get(0);
        final String originalVal = entry.get(langIndex).trim();
        if(originalVal == null || originalVal.isEmpty()) {
          continue;
        }
        final String val = (originalVal.startsWith("\"") && originalVal.endsWith("\"")) ? 
            originalVal.substring(1, originalVal.length()-2) : 
            originalVal;
        
        final String e = String.format("%s<string name=\"%s\">\"%s\"</string>", indent, key, val);

        // System.err.println("entry: "+e);
        writer.write(e);
        writer.newLine();
      }
      writer.write(suffix);
      writer.newLine();
      // writer.flush();
      writer.close(); // don't close stderr
      System.out.printf("file #%d generated: %s%n", langIndex, file); // TODO: merge file?
    }

  }

  private static List<String> readLines(String filename) throws IOException {
    final Scanner s = new Scanner(new File(filename));
    final ArrayList<String> lines = new ArrayList<String>();
    while (s.hasNextLine()){
      lines.add(s.nextLine()); // read everything into memory
    }
    s.close();
    return lines;
  }

  private static List<List<String>> getCells(List<String> lines) {
    List<List<String>> cells = new ArrayList<>();
    for(String line : lines) {
      cells.add(Arrays.asList(line.split(sep, -1))); 
    }
    return cells;
  }

  private static void printUsage() {
    System.out.println("Usage: \njava Convert t.tsv");
  }

  // 'zh-TW' to 'zh-rTW'
  private static String fixLang(String lang) {
    return lang.replaceFirst("([a-zA-Z]{2})-([a-zA-Z])", "$1-r$2");
  }
}
