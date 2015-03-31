public class Transition<E>
{
  private int previousState;
  private char transition;
  private int nextState;
  
  public Transition(int pStat,char trans,int nState)
  {
	previousState = pStat;
	transition = trans;
	nextState = nState;
  }
}
