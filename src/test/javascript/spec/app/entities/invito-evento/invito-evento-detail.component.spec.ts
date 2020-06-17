import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { InvitoEventoDetailComponent } from 'app/entities/invito-evento/invito-evento-detail.component';
import { InvitoEvento } from 'app/shared/model/invito-evento.model';

describe('Component Tests', () => {
  describe('InvitoEvento Management Detail Component', () => {
    let comp: InvitoEventoDetailComponent;
    let fixture: ComponentFixture<InvitoEventoDetailComponent>;
    const route = ({ data: of({ invitoEvento: new InvitoEvento(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [InvitoEventoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(InvitoEventoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InvitoEventoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load invitoEvento on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.invitoEvento).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
