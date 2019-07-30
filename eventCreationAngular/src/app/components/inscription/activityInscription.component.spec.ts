/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { ActivityInscriptionComponent } from './activityInscription.component';

describe('ActivityItemComponent', () => {
  let component: ActivityInscription;
  let fixture: ComponentFixture<ActivityInscription>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivityInscription ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityInscription);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
