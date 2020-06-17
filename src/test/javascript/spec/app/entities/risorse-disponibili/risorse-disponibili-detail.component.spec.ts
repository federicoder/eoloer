import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { RisorseDisponibiliDetailComponent } from 'app/entities/risorse-disponibili/risorse-disponibili-detail.component';
import { RisorseDisponibili } from 'app/shared/model/risorse-disponibili.model';

describe('Component Tests', () => {
  describe('RisorseDisponibili Management Detail Component', () => {
    let comp: RisorseDisponibiliDetailComponent;
    let fixture: ComponentFixture<RisorseDisponibiliDetailComponent>;
    const route = ({ data: of({ risorseDisponibili: new RisorseDisponibili(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [RisorseDisponibiliDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RisorseDisponibiliDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RisorseDisponibiliDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load risorseDisponibili on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.risorseDisponibili).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
