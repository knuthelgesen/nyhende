import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NyhendeComponent } from './nyhende.component';

describe('NyhendeComponent', () => {
  let component: NyhendeComponent;
  let fixture: ComponentFixture<NyhendeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NyhendeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NyhendeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
