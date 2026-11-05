void main() {
    int[] intakeTimes = { 0 };
    int[] mg = { 200 };
    int now = 150;
    IO.println(caffeineAtTime(intakeTimes, mg, now));
}

public static double caffeineAtTime(int[] intakeTimes, int[] mg, int now)  {
    double currCaffeine = 0;

    for (int i = 0; i < intakeTimes.length; ++i)
    {
        int currTime = intakeTimes[i];
        if (currTime > now) break;

        currCaffeine += mg[i] * Math.pow(0.5, ((now - currTime) / 300.0));
    }

    return currCaffeine;
}
