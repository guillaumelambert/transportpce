package com.orange.onap.tpce.manager.openroadm.visual;


import com.orange.onap.tpce.manager.helper.ClliHelper;
import com.orange.onap.tpce.manager.helper.DeviceHelper;
import com.orange.onap.tpce.manager.feature.openroadm.service.OpenRoadmService;
import com.orange.onap.tpce.manager.feature.openroadm.visual.domain.VowlClassAttribute;
import com.orange.onap.tpce.manager.feature.openroadm.visual.domain.VowlProperty;
import com.orange.onap.tpce.manager.feature.openroadm.visual.domain.VowlPropertyAttribute;
import com.orange.onap.tpce.manager.feature.openroadm.visual.domain.WebVowl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisualMapperTest {

    @Autowired
    private OpenRoadmService openRoadmService;

    private WebVowl webVowl = null;

    @Before
    public void setup() throws IOException {
        webVowl = openRoadmService.fetchAndMapNetworks();
    }

    @Test
    public void VowlClassTest() {
        Assert.assertNotNull(webVowl.getVowlClassList());
        Assert.assertFalse(webVowl.getVowlClassList().isEmpty());
        Assert.assertTrue(webVowl.getVowlClassList().get(0).getId().equals(ClliHelper.NODE_ID));
    }

    @Test
    public void VowlClassAttributeTest() {
        try {
            Assert.assertNotNull(webVowl.getClassAttributeList());
            Assert.assertFalse(webVowl.getClassAttributeList().isEmpty());
            Assert.assertTrue(webVowl.getClassAttributeList().get(0).getId().equals(ClliHelper.NODE_ID));

            Assert.assertNotNull(webVowl.getClassAttributeList().get(0).getLabel());
            Assert.assertFalse(webVowl.getClassAttributeList().get(0).getLabel().isEmpty());
            Assert.assertNotNull(webVowl.getClassAttributeList().get(0).getLabel()
                    .get(VowlClassAttribute.LABEL_DEFAULT_KEY).equals(ClliHelper.NODE_ID));

        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail();
        }
    }

    @Test
    @Ignore
    public void VowlClassAttributeWaveLengthTest() {

        Assert.assertNotNull(webVowl.getClassAttributeList().get(0).getWavelengths());
        Assert.assertFalse(webVowl.getClassAttributeList().get(0).getWavelengths().isEmpty());
        Assert.assertTrue(webVowl.getClassAttributeList().get(0).getWavelengths().get(0).equals(DeviceHelper.WAVE_LENGTH_VALUE.toString()));
    }

    @Test
    @Ignore
    public void VowlClassAttributeTerminationPointsTest() {

        Assert.assertNotNull(webVowl.getClassAttributeList().get(0).getTerminationPoints());
        Assert.assertFalse(webVowl.getClassAttributeList().get(0).getTerminationPoints().isEmpty());
        Assert.assertTrue(webVowl.getClassAttributeList().get(0).getTerminationPoints().get(0).equals(DeviceHelper.WAVE_LENGTH_VALUE.toString()));
    }

    @Test
    public void VowlPropertyTest() {
        try {
            Assert.assertNotNull(webVowl.getPropertyList());
            Assert.assertFalse(webVowl.getPropertyList().isEmpty());
            Assert.assertTrue(webVowl.getPropertyList().get(0).getId().equals(DeviceHelper.VOWL_LINK_ID));
            Assert.assertTrue(webVowl.getPropertyList().get(0).getType().equals(VowlProperty.TYPE_DEFAULT_VALUE));

        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail();
        }
    }

    @Test
    public void VowlPropertyAttributeTest() {

        try {

            Assert.assertNotNull(webVowl.getPropertyAttributeList());
            Assert.assertFalse(webVowl.getPropertyAttributeList().isEmpty());

            Assert.assertTrue(webVowl.getPropertyAttributeList().get(0).getId().equals(DeviceHelper.VOWL_LINK_ID));
            Assert.assertTrue(webVowl.getPropertyAttributeList().get(0).getDomain().equals(DeviceHelper.VOWL_SOURCE_NODE));
            Assert.assertTrue(webVowl.getPropertyAttributeList().get(0).getRange().equals(DeviceHelper.VOWL_DESTINATION_NODE));

            //TODO validate label of links between two Roadm, not same RoadM
            Assert.assertNotNull(webVowl.getPropertyAttributeList().get(0).getLabel());
            Assert.assertFalse(webVowl.getPropertyAttributeList().get(0).getLabel().isEmpty());
            Assert.assertTrue(webVowl.getPropertyAttributeList().get(0).getLabel().containsKey(VowlPropertyAttribute.LABEL_DEFAULT_KEY));
            Assert.assertTrue(webVowl.getPropertyAttributeList().get(0).getLabel().get(VowlPropertyAttribute.LABEL_DEFAULT_KEY).equals(" "));
        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail();
        }
    }

// TODO validate xpdr rule
    // TODO scenario where nodes child not exist on parent
}
