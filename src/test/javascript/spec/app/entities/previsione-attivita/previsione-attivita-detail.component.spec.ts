import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { PrevisioneAttivitaDetailComponent } from 'app/entities/previsione-attivita/previsione-attivita-detail.component';
import { PrevisioneAttivita } from 'app/shared/model/previsione-attivita.model';

describe('Component Tests', () => {
  describe('PrevisioneAttivita Management Detail Component', () => {
    let comp: PrevisioneAttivitaDetailComponent;
    let fixture: ComponentFixture<PrevisioneAttivitaDetailComponent>;
    const route = ({ data: of({ previsioneAttivita: new PrevisioneAttivita(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [PrevisioneAttivitaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PrevisioneAttivitaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PrevisioneAttivitaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load previsioneAttivita on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.previsioneAttivita).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
