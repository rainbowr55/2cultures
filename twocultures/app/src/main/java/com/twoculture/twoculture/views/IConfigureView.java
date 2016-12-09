package com.twoculture.twoculture.views;

import com.twoculture.twoculture.models.Configure;

/**
 * Created by liangcaihong on 2016/12/9.
 */

public interface IConfigureView extends IShowMessage{

    void onLoadConfigSuccess(Configure configure);
}
