package ar.edu.unq.americana.extras;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.utils.ExtensionFilter;

public class ImageExtras {

	public static void name(final String dir) throws Exception {
		// load images into an array, e.g. from a directoy
		final List<String> images = load(dir);

		// convert all images
		final ConvertCmd cmd = new ConvertCmd();
		cmd.setAsyncMode(true);
		final IMOperation op = new IMOperation();
		op.addImage(); // place holder for input file
		op.addImage(); // place holder for output file
		for (final String img : images) {
			final String outfile = img.replace(".tif", ".png");
			cmd.run(op, img, outfile);
		}
	}

	private static List<String> load(final String dir) {
		final File current = new File(dir);
		final List<String> tmp = new ArrayList<String>();
		if (current.isDirectory()) {
			load(current, tmp);
		}
		return tmp;
	}

	private static void load(final File current, final List<String> tmp) {
		final String[] files = current.list(new ExtensionFilter("tif"));
		for (final String file : files) {
			tmp.add(file);
		}
		final String[] all = current.list();
		for (final String file : all) {
			if (new File(file).isDirectory()) {
				load(new File(file), tmp);
			}
		}
	}

	public static void convert(final File root, final String targetExtension,
			final String resultExtension) throws Exception {
		final List<String> images = walk(root, targetExtension);

		final ConvertCmd convert = new ConvertCmd();
		convert.setAsyncMode(true);
		// convert all images
		for (final String img : images) {
			final String outfile = img.replace("." + targetExtension, "."
					+ resultExtension);

			final File output = new File(outfile);
			output.delete();
		}
	}

	private static List<String> walk(final File root,
			final String targetExtension) {
		final List<String> tmp = new ArrayList<String>();
		walk(root, targetExtension, tmp);
		return tmp;

	}

	private static void walk(final File root, final String targetExtension,
			final List<String> tmp) {
		final File[] list = root.listFiles();

		if (list == null) {
			return;
		}

		for (final File f : list) {
			if (f.isDirectory()) {
				walk(f, targetExtension, tmp);
			} else {
				if (f.getName().endsWith("." + targetExtension)) {
					System.err.println("File:" + f.getAbsolutePath());
					tmp.add(f.getAbsolutePath());
				}
			}
		}
	}

}
