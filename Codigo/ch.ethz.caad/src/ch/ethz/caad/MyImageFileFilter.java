package ch.ethz.caad;

import java.io.File;

import javax.swing.filechooser.FileFilter;


/**
 * Example for a ImageFileFilter
 */
public class MyImageFileFilter extends FileFilter{
	/**
	 * define which file extension is accepted
	 */
	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String extension = Utils.getExtension(f);
		if (extension != null) {
			if (extension.equals(Utils.tiff) 
					|| extension.equals(Utils.tif)
					|| extension.equals(Utils.gif)
					|| extension.equals(Utils.jpeg)
					|| extension.equals(Utils.jpg)
					|| extension.equals(Utils.png)) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}
	
	/**
	 * define the description of this fileFilter
	 * for the JFileChooser
	 */
	@Override
	public String getDescription() {
		return "ImageFiles";
	}
	
	/**
	 * Simple utility to compare file extensions
	 */
	private static class Utils {
		public final static String jpeg = "jpeg";
	    public final static String jpg = "jpg";
	    public final static String gif = "gif";
	    public final static String tiff = "tiff";
	    public final static String tif = "tif";
	    public final static String png = "png";

	    /*
	     * Get the extension of a file.
	     */  
	    public static String getExtension(File f) {
	        String ext = null;
	        String s = f.getName();
	        int i = s.lastIndexOf('.');

	        if (i > 0 &&  i < s.length() - 1) {
	            ext = s.substring(i+1).toLowerCase();
	        }
	        return ext;
	    }
	}

}
