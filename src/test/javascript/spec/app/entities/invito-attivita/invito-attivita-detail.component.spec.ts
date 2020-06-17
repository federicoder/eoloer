import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { InvitoAttivitaDetailComponent } from 'app/entities/invito-attivita/invito-attivita-detail.component';
import { InvitoAttivita } from 'app/shared/model/invito-attivita.model';

describe('Component Tests', () => {
  describe('InvitoAttivita Management Detail Component', () => {
    let comp: InvitoAttivitaDetailComponent;
    let fixture: ComponentFixture<InvitoAttivitaDetailComponent>;
    const route = ({ data: of({ invitoAttivita: new InvitoAttivita(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [InvitoAttivitaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(InvitoAttivitaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InvitoAttivitaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load invitoAttivita on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.invitoAttivita).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
