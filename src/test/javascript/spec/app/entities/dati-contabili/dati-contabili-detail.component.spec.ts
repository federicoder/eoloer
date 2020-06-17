import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { DatiContabiliDetailComponent } from 'app/entities/dati-contabili/dati-contabili-detail.component';
import { DatiContabili } from 'app/shared/model/dati-contabili.model';

describe('Component Tests', () => {
  describe('DatiContabili Management Detail Component', () => {
    let comp: DatiContabiliDetailComponent;
    let fixture: ComponentFixture<DatiContabiliDetailComponent>;
    const route = ({ data: of({ datiContabili: new DatiContabili(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [DatiContabiliDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DatiContabiliDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DatiContabiliDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load datiContabili on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.datiContabili).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
