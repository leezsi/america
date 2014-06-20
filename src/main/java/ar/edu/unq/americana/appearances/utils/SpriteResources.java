package ar.edu.unq.americana.appearances.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ar.edu.unq.americana.appearances.Animation;
import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.exceptions.GameException;

@XmlRootElement(name = "resource")
public class SpriteResources {

	@XmlElement(name = "sprites")
	private final List<SpriteResources> spriteResources = new ArrayList<SpriteResources>();

	@XmlElement(name = "animation")
	private final List<SpriteResources> animationResources = new ArrayList<SpriteResources>();

	@XmlAttribute
	private String img;

	@XmlElement(name = "sprite")
	private List<SpriteResources> sprites;

	@XmlAttribute
	private String name;

	@XmlElement(name = "crop")
	private SpriteResources bounds;

	@XmlAttribute
	private Double x, y, width, height;

	@XmlElement(name = "ref")
	private List<SpriteResources> referencies;

	@XmlAttribute
	private double meantime;

	@XmlAttribute
	private String to;

	private static Map<String, SpriteResources> resources = new HashMap<String, SpriteResources>();
	private final Map<String, Animation> allAnimations = new HashMap<String, Animation>();
	private final Map<String, Sprite> allSprites = new HashMap<String, Sprite>();
	private String resource;

	public static Sprite sprite(final String resourcePath, final String resource) {
		return get(resourcePath, resource).allSprites.get(resource).copy();
	}

	public static Animation animation(final String resourcePath,
			final String resource) {
		return get(resourcePath, resource).allAnimations.get(resource).copy();
	}

	private static SpriteResources get(final String resourcePath,
			final String resource) {
		if (!resources.containsKey(resourcePath)) {
			final SpriteResources res = readFile(resourcePath);
			res.resource = resourcePath;
			res.orderSprites();
			res.orderAnimations();
			resources.put(resourcePath, res);
		}
		return resources.get(resourcePath);
	}

	private static SpriteResources readFile(final String resource) {
		try {
			final JAXBContext jaxbContext = JAXBContext
					.newInstance(SpriteResources.class);
			final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			return (SpriteResources) unmarshaller.unmarshal(ClassLoader
					.getSystemResource(resource + ".resource.xml"));
		} catch (final Exception e) {
			throw new GameException(e);
		}
	}

	private void orderAnimations() {
		for (final SpriteResources animationResource : this.animationResources) {
			final List<Sprite> tmp = new ArrayList<Sprite>();
			for (final SpriteResources ref : animationResource.referencies) {
				Sprite sprite = this.allSprites.get(ref.to);
				if (animationResource.width != null) {
					sprite = sprite.scaleHorizontally(animationResource.width
							/ sprite.getWidth());
				}
				if (animationResource.height != null) {
					sprite = sprite.scaleVertically(animationResource.height
							/ sprite.getHeight());
				}
				tmp.add(sprite);
			}
			final Animation animation = new Animation(
					animationResource.meantime, tmp.toArray(new Sprite[tmp
							.size()]));

			this.allAnimations.put(animationResource.name, animation);
		}
	}

	private void orderSprites() {
		for (final SpriteResources _sprites : this.spriteResources) {
			final Sprite base = Sprite.fromImage(this.resource + "/../"
					+ _sprites.img);
			for (final SpriteResources sprite : _sprites.sprites) {
				final SpriteResources bounds = sprite.bounds;
				final int x = (int) bounds.x.doubleValue();
				final int y = (int) bounds.y.doubleValue();
				final int width = (int) (bounds.width == null ? base.getWidth()
						: bounds.width.doubleValue());
				final int height = (int) (bounds.height == null ? base
						.getHeight() : bounds.height.doubleValue());
				Sprite crop = base.crop(x, y, width, height);
				if (sprite.width != null) {
					crop = crop.scaleHorizontally(sprite.width
							/ crop.getWidth());
				}

				if (sprite.height != null) {
					crop = crop.scaleVertically(sprite.height
							/ crop.getHeight());
				}
				this.allSprites.put(sprite.name, crop);
			}
		}
	}

}
