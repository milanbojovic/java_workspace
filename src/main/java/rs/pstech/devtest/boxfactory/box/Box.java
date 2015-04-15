package rs.pstech.devtest.boxfactory.box;

public class Box implements Runnable
{
	private int productionDurationTime = 0;
	private String type = null;

	public Box(int productionDurationTime, String type){
		this.productionDurationTime = productionDurationTime;
		this.type = type;
	}
	
	public void produceBox() {
		
		try {
			Thread.sleep(productionDurationTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Box " + type + " created");
	}

	public void run() {
		// TODO Auto-generated method stub
		produceBox();
	};
	
}