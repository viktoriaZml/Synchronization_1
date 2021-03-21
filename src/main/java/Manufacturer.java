public class Manufacturer {
  private final Autoshop autoshop;

  public Manufacturer(Autoshop autoshop) {
    this.autoshop = autoshop;
  }

  public synchronized void releaseCar() {
    autoshop.getCars().add(new Car());
    System.out.printf("Производитель %s выпустил 1 авто\n", Thread.currentThread().getName());
    notify();
  }

  public synchronized Car sellCar() {
    try {
      while (autoshop.getCars().size() == 0) {
        System.out.println("Машин нет");
        wait();
      }
      System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
    } catch (InterruptedException e) {
      System.out.println(Thread.currentThread().getName() + " ушел без покупки");
      return null;
    }
    return autoshop.getCars().remove(0);
  }
}
