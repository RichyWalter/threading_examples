class CounterCommand implements Runnable
{
  @Override public void run()
  {
    for ( int i = 0; i < 200; i++ )
        System.out.println( i );
  }
}