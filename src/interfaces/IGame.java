package interfaces;

public interface IGame {
	public void update(long deltaMillis);
	public void draw(long deltaMillis);
	public void init();
}
