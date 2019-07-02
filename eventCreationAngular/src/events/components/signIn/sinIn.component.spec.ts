/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { SinInComponent } from './sinIn.component';

describe('SinInComponent', () => {
  let component: SinInComponent;
  let fixture: ComponentFixture<SinInComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SinInComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SinInComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
