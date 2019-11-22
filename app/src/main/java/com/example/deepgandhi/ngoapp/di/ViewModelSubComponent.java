package com.example.deepgandhi.ngoapp.di;

/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import com.example.deepgandhi.ngoapp.viewModels.CommonViewModel;

import dagger.Subcomponent;

/**
 * A sub component to create ViewModels. It is called by the
     ViewmodelFactory Using this component allows
 * ViewModels to define {@link javax.inject.Inject} constructors.
 */


@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    CommonViewModel commonViewModel();

}