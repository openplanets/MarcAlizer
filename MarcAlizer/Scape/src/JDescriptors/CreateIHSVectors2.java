package JDescriptors;

import java.io.File;
import java.util.ArrayList;

import fr.lip6.jdescriptors.color.ColorVQDescriptorCreator;
import fr.lip6.jdescriptors.color.ColorVQFloatDescriptor;
import fr.lip6.jdescriptors.color.model.IHSColorQuantizer;
import fr.lip6.jdescriptors.detector.HoneycombDetector;
import fr.lip6.jdescriptors.io.XMLWriter;

public class CreateIHSVectors2 {

	/**
	 * @param args
	 */
	public static void run(String srcDir, String dstDir, int I, int H, int S, int s, int r, int maxHeight, boolean onlyTop) {
		// TODO Auto-generated method stub
		
		ColorVQDescriptorCreator c = ColorVQDescriptorCreator.getInstance();
		//System.out.println(" s = " + s + " ; r = " + s + " ; cut = " + cut);
		
		//Descriptor creator
		HoneycombDetector detector = new HoneycombDetector(s, r);
		c.setDetector(detector); 
		IHSColorQuantizer q = new IHSColorQuantizer(I, H, S);
		c.setQuantizer(q);
		c.setNormalize(false);
		
		File src = new File(srcDir);
		if(!src.exists() || !src.isDirectory())
		{
			System.out.println(srcDir+" : No such directory !");
			return;
		}
		
		String[] files2 = src.list();
		if(files2 == null)
		{
			System.out.println("No files in "+srcDir);
			return;
		}
		//(new File(dstDir)).mkdir();
		for (String f2 : files2) {
		System.out.println("f2 = " + dstDir+"/"+f2);

		(new File(dstDir+"/"+f2)).mkdir();
		for(String f3 : new File(srcDir+"/"+f2).list()){
		(new File(dstDir+"/"+f2+"/"+f3)).mkdir();
		for(String f : new File(srcDir+"/"+f2+"/"+f3).list())
		
		{
			/*if (!(f.endsWith(".png"))){
				continue;
			}*/
			try
			{
				//if (!(new File(dstDir+f2+"/"+f.substring(0, f.indexOf('.'))+".xgz").exists()) && !f.equals("280.png") && !f.equals("281.png") ){
					//System.out.println(f);
				System.out.println("lecture de " + srcDir+"/"+f2+"/"+f3+"/"+f);
				if (!(new File(dstDir+f2+"/"+f3+"/"+f.substring(0, f.indexOf('.'))+".xgz").exists())) {
				ArrayList<ColorVQFloatDescriptor> list = c.createDescriptors(srcDir+"/"+f2+"/"+f3+"/"+f, maxHeight, onlyTop);
				XMLWriter.writeXMLFile(dstDir+"/"+f2+"/"+f3+"/"+f.substring(0, f.indexOf('.')), list, true);
				System.out.println(f+" descriptor written : taille = " + list.size());
				}
			}
			catch(Exception ioe)
			{
				System.err.println("no descriptors for "+f);
				ioe.printStackTrace();
			}
		}
		}
		}

	}

}
