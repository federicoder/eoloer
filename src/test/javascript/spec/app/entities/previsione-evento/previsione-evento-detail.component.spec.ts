import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { PrevisioneEventoDetailComponent } from 'app/entities/previsione-evento/previsione-evento-detail.component';
import { PrevisioneEvento } from 'app/shared/model/previsione-evento.model';

describe('Component Tests', () => {
  describe('PrevisioneEvento Management Detail Component', () => {
    let comp: PrevisioneEventoDetailComponent;
    let fixture: ComponentFixture<PrevisioneEventoDetailComponent>;
    const route = ({ data: of({ previsioneEvento: new PrevisioneEvento(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [PrevisioneEventoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PrevisioneEventoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PrevisioneEventoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load previsioneEvento on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.previsioneEvento).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
