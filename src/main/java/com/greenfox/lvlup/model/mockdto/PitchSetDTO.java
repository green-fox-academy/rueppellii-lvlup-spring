package com.greenfox.lvlup.model.mockdto;

import java.util.ArrayList;
import java.util.List;

public class PitchSetDTO {

  private List<PitchDTO> myPitches;
  private List<PitchDTO> pitchesToReview;

  public PitchSetDTO() {
    List<PitchDTO> pitchList = new ArrayList<>();
    List<PitchDTO> pitchList2 = new ArrayList<>();
    PitchDTO pitch1 = new PitchDTO("2018-11-29 17:10:47", "balazs.barna", "Programming", 2, 3, "I improved in React, Redux, basic JS, NodeJS, Express and in LowDB, pls give me more money");
    PitchDTO pitch2  = new PitchDTO("2018-11-29 17:10:47", "berei.daniel", "English speaker", 2, 3, "I was working abroad for six years, so I can speak english very well. Pls improve my badge level to 3.");
    pitchList.add(pitch1);
    pitchList2.add(pitch2);
    setMyPitches(pitchList);
    setPitchesToReview(pitchList2);
  }

  public List<PitchDTO> getMyPitches() {
    return myPitches;
  }

  public void setMyPitches(List<PitchDTO> myPitches) {
    this.myPitches = myPitches;
  }

  public List<PitchDTO> getPitchesToReview() {
    return pitchesToReview;
  }

  public void setPitchesToReview(List<PitchDTO> pitchesToReview) {
    this.pitchesToReview = pitchesToReview;
  }
}
