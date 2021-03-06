package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.strategy.ManualNumberStrategy;
import lotto.strategy.NumberStrategy;
import lotto.strategy.RandomNumberStrategy;
import lotto.util.InputUtil;

public class LottoTicket {
  private static final int LOTTO_NUMBERS_SIZE = 6;
  private static final int LOTTO_MAX_NUMBER = 45;
  private static final int LOTTO_MIN_NUMBER = 1;

  private List<Integer> lottoNumbers = new ArrayList<>();

  private LottoTicket(NumberStrategy numberStrategy) {
    lottoNumbers.addAll(numberStrategy.generateNumbers(LOTTO_NUMBERS_SIZE, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER));
  }

  public static LottoTicket of(List<String> ticketInputs) {
    List<Integer> ticketNumbers = ticketInputs.stream()
        .map(InputUtil::parseStringToInt)
        .collect(Collectors.toList());
    return new LottoTicket(new ManualNumberStrategy(ticketNumbers));
  }

  public static LottoTicket ofRandom() {
    return new LottoTicket(new RandomNumberStrategy());
  }

  public void showLottoNumbers() {
    System.out.println(Arrays.toString(lottoNumbers.toArray()));
  }

  public int calculateNumOfMatch(List<Integer> winningNumbers) {
    return (int) lottoNumbers.stream()
        .filter(winningNumbers::contains)
        .count();
  }

  public boolean hasMatchingNumWith(int ball) {
    return lottoNumbers.contains(ball);
  }

}
