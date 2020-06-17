import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { LineaOrdineDetailComponent } from 'app/entities/linea-ordine/linea-ordine-detail.component';
import { LineaOrdine } from 'app/shared/model/linea-ordine.model';

describe('Component Tests', () => {
  describe('LineaOrdine Management Detail Component', () => {
    let comp: LineaOrdineDetailComponent;
    let fixture: ComponentFixture<LineaOrdineDetailComponent>;
    const route = ({ data: of({ lineaOrdine: new LineaOrdine(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [LineaOrdineDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(LineaOrdineDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LineaOrdineDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load lineaOrdine on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.lineaOrdine).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
